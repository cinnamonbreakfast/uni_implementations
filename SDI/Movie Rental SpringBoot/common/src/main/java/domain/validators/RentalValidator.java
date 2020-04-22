package domain.validators;

import domain.Rental;
import domain.exceptions.ValidatorException;

public class RentalValidator implements Validator<Rental> {

    /**
     * Validate the fields of a rental record (including the unique ID).
     * @param entity
     * @throws ValidatorException
     */
    @Override
    public void validate(Rental entity) throws ValidatorException {
        if(entity.getClient()< 0)
            throw new ValidatorException("Client ID cannot be negative.");
        else
        if(entity.getMovie() < 0)
            throw new ValidatorException("Movie ID cannot be negative.");
        else if(!entity.getId().equals((1/2)*(entity.getClient()+entity.getMovie())*(entity.getMovie()+entity.getClient()+1)+entity.getMovie()))
            throw new ValidatorException("Rental ID is not valid (entity corrupted).");


    }
}