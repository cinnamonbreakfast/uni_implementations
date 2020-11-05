package service;
import Domain.Movie;
import Domain.Validators.ValidatorException;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieServiceImpl implements MovieService {

    private Repository<Long, Movie> repository;
    private ExecutorService executorService;
    public MovieServiceImpl(Repository<Long, Movie> repository, ExecutorService service) {
        this.repository = repository;
        executorService = service;
    }

    @Override
    public CompletableFuture<Void> addMovie(Movie movie) throws ValidatorException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Optional<Movie> movies = repository.save(movie);
                if(movies.isPresent())
                    return null;
                throw new Exception("Could not add the movie.");
            } catch (Exception e) {
                throw new RuntimeException(e.getMessage());
            }
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> getAllMovies(String... sort) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sorted = new Sort(sort);
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sorted);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> filterMovieByName(String s) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("name");
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .filter((e)-> e.getName().equals(s))
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> filterMovieByDescription(String d) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("name");
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .filter((e)-> e.getDescription().equals(d))
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> filterMovieByPrice(int p) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("name");
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .filter((e)-> e.getPrice()==p)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> filterMovieByRating(int r) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort("name");
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .filter((e)-> e.getRating()==r)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> removeMovie(Long id) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Movie> movie = repository.delete(id);
            if(movie.isPresent())
                return null;
            throw new RuntimeException("No movie to delete.");
        }, executorService);
    }

    @Override
    public CompletableFuture<Void> updateMovie(Movie entity) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Movie> movie = repository.update(entity);
            if(movie.isPresent())
                return null;
            throw new RuntimeException("No movie to update.");
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> getAll() {
        return CompletableFuture.supplyAsync(() -> {
            Iterable<Movie> movies = repository.findAll();
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> getAllSortedAscendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.ASC, fields);
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }

    @Override
    public CompletableFuture<List<Movie>> getAllSortedDescendingByFields(String... fields) {
        return CompletableFuture.supplyAsync(() -> {
            Sort sort = new Sort(Sort.Direction.DESC, fields);
            Iterable<Movie> movies = ((SortingRepository<Long, Movie>)repository).findAll(sort);
            return StreamSupport.stream(
                    movies.spliterator(),
                    false)
                    .collect(Collectors.toList());
        }, executorService);
    }



    @Override
    public CompletableFuture<Movie> getByID(Long aLong) {
        return CompletableFuture.supplyAsync(() -> {
            Optional<Movie> movie = repository.findOne(aLong);
            if(movie.isPresent())
                return movie.get();
            throw new RuntimeException("Could not find movie by given ID.");
        }, executorService);
    }
}
