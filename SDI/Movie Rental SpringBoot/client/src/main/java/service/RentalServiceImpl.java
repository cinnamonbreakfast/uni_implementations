package service;

import domain.Rental;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class RentalServiceImpl implements RentalService {

    @Autowired
    RentalService rentalService;

    @Override
    public void addRental(Rental entity) throws ValidatorException, IOException {
        rentalService.addRental(entity);
    }

    @Override
    public void removeRental(Long ID) {
        rentalService.removeRental(ID);
    }

    @Override
    public Long mostRentedMovie() {
        return rentalService.mostRentedMovie();
    }

    @Override
    public Long mostLoyalCustomer() {
        return rentalService.mostLoyalCustomer();
    }

    @Override
    public List<Rental> getAll() {
        return rentalService.getAll();
    }

    @Override
    public List<Rental> getAllSortedAscendingByFields(String... fields) {
        return rentalService.getAllSortedAscendingByFields(fields);
    }

    @Override
    public List<Rental> getAllSortedDescendingByFields(String... fields) {
        return rentalService.getAllSortedDescendingByFields(fields);
    }

    @Override
    public Rental getByID(Long aLong) {
        return rentalService.getByID(aLong);
    }
}
