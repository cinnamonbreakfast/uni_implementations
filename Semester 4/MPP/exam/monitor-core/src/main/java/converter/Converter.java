package converter;

import domain.Entity;
import dto.BaseDTO;

public interface Converter<Model extends Entity<Long>, Dto extends BaseDTO> {

    Model convertDtoToModel(Dto dto);

    Dto convertModelToDto(Model model);

}
