package service;

import domain.Sensor;
import dto.SensorDTO;

import java.util.concurrent.CompletableFuture;

public interface SensorService extends Service<Long, Sensor> {
    CompletableFuture<SensorDTO> addSensor(SensorDTO entity);
    CompletableFuture<Integer> sendValue(Sensor sensor);
}
