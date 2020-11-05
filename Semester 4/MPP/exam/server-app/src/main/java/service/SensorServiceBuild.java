package service;

import converter.SensorConverter;
import domain.Sensor;
import dto.SensorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.SensorRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

@Service
public class SensorServiceBuild implements SensorService {

    private final SensorRepository sensorRepository;
    private final ExecutorService executorService;
    private final SensorConverter sensorConverter;
    // Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()) in main

    @Autowired
    public SensorServiceBuild(SensorRepository sensorRepository, ExecutorService executorService, SensorConverter sensorConverter) {
        this.sensorRepository = sensorRepository;
        this.executorService = executorService;
        this.sensorConverter = sensorConverter;
    }

    @Override
    public CompletableFuture<SensorDTO> addSensor(SensorDTO entity) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Sensor sensor = sensorConverter.convertDtoToModel(entity);
                sensorRepository.save(sensor);

                return entity;
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Integer> sendValue(Sensor sensor) {
        return null;
    }

    @Override
    public CompletableFuture<List<Sensor>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return sensorRepository.findAll();
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }
}
