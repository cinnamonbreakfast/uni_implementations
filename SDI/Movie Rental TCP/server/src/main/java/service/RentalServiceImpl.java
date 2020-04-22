package service;
import Domain.Rental;
import Domain.Validators.ValidatorException;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class RentalServiceImpl implements RentalService {

    private Repository<Long, Rental> repository;
    private ExecutorService executorService;

    public RentalServiceImpl(Repository<Long, Rental> repository, ExecutorService executorService) {
        this.repository = repository;
        this.executorService = executorService;
    }

    @Override
    public CompletableFuture<Void> addRental(Rental entity) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Optional<Rental> rental = repository.save(entity);
                if(rental.isPresent())
                    return null;
                throw new Exception("Could not add the rental.");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> removeRental(Long ID) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Rental> rental = repository.delete(ID);
            if(rental.isPresent())
                return null;
            throw new RuntimeException("No rental to delete.");
        }, executorService);
    }

    @Override
    public CompletableFuture<Long> mostRentedMovie() {
        Iterable<Rental> rentals = repository.findAll();
        List<Long> listOfMovies = new ArrayList<Long>();
        rentals.forEach(x->listOfMovies.add(x.getMovie()));
        return CompletableFuture.supplyAsync(() -> { listOfMovies.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse((long) -1);
            return null;
        }, executorService);
    }

    @Override
    public CompletableFuture<Long> mostLoyalCustomer() {
        Iterable<Rental> rentals = repository.findAll();
        List<Long> listOfMovies = new ArrayList<Long>();
        rentals.forEach(x->listOfMovies.add(x.getClient()));
        return CompletableFuture.supplyAsync(() -> { listOfMovies.stream().
                collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse((long) -1);
                return null;
            }, executorService);
    }

    @Override
    public CompletableFuture<List<Rental>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Rental> rentals = repository.findAll();
            return StreamSupport.stream(
                    rentals.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Rental>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.ASC, fields);
            Iterable<Rental> rentals = ((SortingRepository<Long, Rental>)repository).findAll(sort);
            return StreamSupport.stream(
                    rentals.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Rental>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.DESC, fields);
            Iterable<Rental> rentals = ((SortingRepository<Long, Rental>)repository).findAll(sort);
            return StreamSupport.stream(
                    rentals.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<Rental> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Rental> rental = repository.findOne(aLong);
            if(rental.isPresent())
                return rental.get();
            throw new RuntimeException("Could not find rental by given ID.");
        }, executorService);
    }
}
