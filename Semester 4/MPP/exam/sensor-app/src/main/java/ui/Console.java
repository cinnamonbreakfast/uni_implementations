package ui;

import dto.SensorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.SensorService;

import java.util.Scanner;

@Component
public class Console {
    private final SensorService sensorService;
    private static Scanner keyboard = new Scanner(System.in);

    @Autowired
    public Console(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    public void addSensor() {
        String name = keyboard.nextLine();
        Integer lb = keyboard.nextInt();
        Integer ub = keyboard.nextInt();

        SensorDTO sensorDTO = SensorDTO.builder()
                .name(name)
                .upperBound(ub)
                .lowerBound(lb)
                .build();

        System.out.println(sensorDTO);

        this.sensorService.addSensor(sensorDTO).thenAccept(
                r -> System.out.println("Added.")
        );
    }
}
