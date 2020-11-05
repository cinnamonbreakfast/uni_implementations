package domain;

import lombok.*;

import javax.persistence.Column;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Sensor extends Entity<Long> {
    @Column(name = "sensorName")
    String name;

    @Column(name = "lowerBound")
    Integer lowerBound;

    @Column(name = "upperBound")
    Integer upperBound;
}
