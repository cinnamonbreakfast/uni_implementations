package service;

import domain.Rental;
import domain.exceptions.ValidatorException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface RentalService extends Service<Long, Rental> {
    void addRental(Rental entity) throws ValidatorException, IOException;
    void removeRental(Long ID);
    Long mostRentedMovie();
    Long mostLoyalCustomer();
}
