package dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BaseDTO<ID> {
    private ID id;
}
