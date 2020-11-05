package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
public class SensorDTO extends BaseDTO<Long> {
    String name;
    Integer lowerBound;
    Integer upperBound;
}
