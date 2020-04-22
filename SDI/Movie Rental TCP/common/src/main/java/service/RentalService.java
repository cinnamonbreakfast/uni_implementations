package service;

import Domain.Rental;
import Domain.Validators.ValidatorException;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public interface RentalService extends Service<Long, Rental> {
    CompletableFuture<Void> addRental(Rental entity) throws ValidatorException, IOException;
    CompletableFuture<Void> removeRental(Long ID);
    CompletableFuture<Long> mostRentedMovie();
    CompletableFuture<Long> mostLoyalCustomer();
}
