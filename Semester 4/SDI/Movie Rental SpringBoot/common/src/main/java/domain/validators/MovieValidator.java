package domain.validators;

import domain.Movie;
import domain.exceptions.ValidatorException;

public class MovieValidator implements Validator<Movie> {

    /**
     * Validate the fields of a Movie entity.
     *
     * @param entity
     * @throws ValidatorException
     */
    @Override
    public void validate(Movie entity) throws ValidatorException {
        //TODO

        if(entity.getName().length()==0)
            throw new ValidatorException("The name is null");
        else
        if(entity.getDescription().length()==0)
            throw new ValidatorException("The description is null");
        else if(entity.getPrice()<0 && entity.getPrice()>24)
            throw new ValidatorException("The price is not ok");


    }
}

