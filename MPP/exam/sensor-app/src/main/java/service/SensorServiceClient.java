package service;

import domain.Sensor;
import dto.SensorDTO;
import message.Message;
import message.MessageHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcp.TcpClient;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class SensorServiceClient implements SensorService {
    private final ExecutorService executorService;
    private final TcpClient tcpClient;

    @Autowired
    public SensorServiceClient(ExecutorService executorService, TcpClient tcpClient) {
        this.executorService = executorService;
        this.tcpClient = tcpClient;
    }


    @Override
    public CompletableFuture<SensorDTO> addSensor(SensorDTO entity) {
        return CompletableFuture.supplyAsync(() -> {
            ArrayList<Map.Entry<String, Object>> body = new ArrayList<>();
            body.add(new AbstractMap.SimpleEntry<>("entity", entity));

            Message request = new Message(MessageHeader.SENSOR_ADD, body);
            Message response = tcpClient.sendAndReceive(request);
            if(response.getHeader().equals(MessageHeader.OK))
            {
                return null;
            }
            else if(response.getHeader().equals(MessageHeader.ERROR))
            {
                System.out.println("ERROR");

            } else {
                System.out.println("unknown");
            }
            return entity;
        }, executorService).exceptionally(null);
    }

    @Override
    public CompletableFuture<Integer> sendValue(Sensor sensor) {
        return null;
    }

    @Override
    public CompletableFuture<List<Sensor>> getAll() {
        return null;
    }
}

