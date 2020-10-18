package service;

import domain.Sensor;
import dto.SensorDTO;
import message.Message;
import message.MessageHeader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tcp.TcpServer;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.ArrayList;

@Service
public class ServerController {
    private final TcpServer tcpServer;
    private final SensorService sensorService;

    @Autowired
    public ServerController(TcpServer tcpServer, SensorService sensorService) {
        this.tcpServer = tcpServer;
        this.sensorService = sensorService;
    }

    public Message ok(String field, String msg)
    {
        Message response = new Message(MessageHeader.OK, new ArrayList<>());
        response.getBody().add(new AbstractMap.SimpleEntry<>(field, msg));
        return response;
    }

    public Message error(String field, String msg)
    {
        Message response = new Message(MessageHeader.ERROR, new ArrayList<>());
        response.getBody().add(new AbstractMap.SimpleEntry<>(field, msg));
        return response;
    }

    public void linkHandlersToHeader() {
        this.tcpServer.addHandler(MessageHeader.SENSOR_ADD,
            request -> this.sensorService.addSensor(
                    (SensorDTO) request.getBody().get(0).getValue()
            ).thenApply(
                    client -> ok("message", "Sensor added.")
            ).exceptionally(
                    err -> error("message", err.getMessage())
            ).join()
        );
    }

    public void runServer()
    {
        linkHandlersToHeader();
        try
        {
            System.out.println("Server started.");
            tcpServer.startServer();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
