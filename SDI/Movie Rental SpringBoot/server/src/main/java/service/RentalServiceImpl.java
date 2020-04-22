package service;

import domain.Rental;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.io.IOException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RentalServiceImpl implements RentalService{
    @Autowired
    private Repository<Long, Rental> rentalRepository;

    @Override
    public void addRental(Rental entity) throws ValidatorException {

        try {
            Optional<Rental> rental = rentalRepository.save(entity);
            if(rental.isEmpty())
                throw new Exception("Could not add the rental.");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public void removeRental(Long ID) {
        Optional<Rental> rental = rentalRepository.delete(ID);
        if(rental.isEmpty())
            throw new RuntimeException("No rental to delete.");
    }

    @Override
    public Long mostRentedMovie() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        List<Long> listOfMovies = new ArrayList<Long>();
        rentals.forEach(x->listOfMovies.add(x.getMovie()));
        listOfMovies.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse((long) -1);
            return null;
    }

    @Override
    public Long mostLoyalCustomer() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        List<Long> listOfMovies = new ArrayList<Long>();
        rentals.forEach(x->listOfMovies.add(x.getClient()));
        listOfMovies.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse((long) -1);
            return null;
    }

    @Override
    public List<Rental> getAll() {
        Iterable<Rental> rentals = rentalRepository.findAll();
        return StreamSupport.stream(
                rentals.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> getAllSortedAscendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        Iterable<Rental> rentals = ((SortingRepository<Long, Rental>)rentalRepository).findAll(sort);
        return StreamSupport.stream(
                rentals.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> getAllSortedDescendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        Iterable<Rental> rentals = ((SortingRepository<Long, Rental>)rentalRepository).findAll(sort);
        return StreamSupport.stream(
                rentals.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Rental getByID(Long aLong) {
        Optional<Rental> rental = rentalRepository.findOne(aLong);
        if(rental.isPresent())
            return rental.get();
        throw new RuntimeException("Could not find rental by given ID.");
    }
}
