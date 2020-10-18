package converter;

import domain.Sensor;
import dto.SensorDTO;
import org.springframework.stereotype.Component;

@Component
public class SensorConverter extends BaseConverter<Sensor, SensorDTO> {

    public Sensor convertDtoToModel(SensorDTO dto){
        Sensor sensor = Sensor.builder()
                .name(dto.getName())
                .lowerBound(dto.getLowerBound())
                .upperBound(dto.getUpperBound())
                .build();
        sensor.setId(dto.getId());
        return sensor;
    }

    public SensorDTO convertModelToDto(Sensor model){
        SensorDTO sensorDTO = SensorDTO.builder()
                .name(model.getName())
                .lowerBound(model.getLowerBound())
                .upperBound(model.getUpperBound())
                .build();
        sensorDTO.setId(model.getId());
        return sensorDTO;
    }
}
