package Domain.Validators;

import Domain.Client;

public class ClientValidator implements Validator<Client> {

    /**
     * Validate the fields of the Client entity (to match the type and logic, not the integrity in the software, e.g. duplicates).
     *
     * @param entity
     * @throws ValidatorException
     */
    @Override
    public void validate(Client entity) throws ValidatorException {
        if(entity.getFirstName() == "")
            throw new ValidatorException("First name cannot be empty.");
        if(entity.getSecondName() == "")
            throw new ValidatorException("Last name cannot be empty.");
        if(entity.getAge() < 12)
            throw new ValidatorException("Age cannot be less than 12.");
    }
}
