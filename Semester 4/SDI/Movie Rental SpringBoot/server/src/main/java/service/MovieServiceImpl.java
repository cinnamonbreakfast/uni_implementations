package service;

import domain.Client;
import domain.Movie;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import repository.Repository;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieServiceImpl implements MovieService{
    @Autowired
    private Repository<Long, Movie> movieRepository;

    @Override
    public void addMovie(Movie movie) throws ValidatorException, IOException {
        try {
            Optional<Movie> clients = movieRepository.save(movie);
            if(clients.isEmpty())
                throw new Exception("Could not memorise the movie.");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<Movie> getAllMovies(String... sort) {
        Sort sorted = new Sort(sort);
        Iterable<Movie> clients = ((SortingRepository<Long, Movie>)movieRepository).findAll(sorted);
        return StreamSupport.stream(
                clients.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterMovieByName(String s) {
        Sort sort = new Sort("name");
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .filter((e)-> e.getName().equals(s))
                .collect(Collectors.toList());

    }

    @Override
    public List<Movie> filterMovieByDescription(String d) {
        Sort sort = new Sort("description");
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .filter((e)-> e.getDescription().equals(d))
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterMovieByPrice(int p) {
        Sort sort = new Sort("price");
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .filter((e)->e.getPrice()==p)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> filterMovieByRating(int r) {
        Sort sort = new Sort("rating");
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .filter((e)->e.getRating()==r)
                .collect(Collectors.toList());
    }

    @Override
    public void removeMovie(Long id) {
        Optional<Movie> movie = movieRepository.delete(id);
        if(movie.isEmpty())
            throw new RuntimeException("No movie to delete.");
    }

    @Override
    public void updateMovie(Movie entity) {
        Optional<Movie> movie = movieRepository.update(entity);
        if(movie.isEmpty())
            throw new RuntimeException("No movie to update.");
    }

    @Override
    public List<Movie> getAll() {
        Iterable<Movie> movies = movieRepository.findAll();
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllSortedAscendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.ASC, fields);
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public List<Movie> getAllSortedDescendingByFields(String... fields) {
        Sort sort = new Sort(Sort.Direction.DESC, fields);
        Iterable<Movie> movies = ((SortingRepository<Long, Movie>)movieRepository).findAll(sort);
        return StreamSupport.stream(
                movies.spliterator(),
                false)
                .collect(Collectors.toList());
    }

    @Override
    public Movie getByID(Long aLong) {
        Optional<Movie> movie = movieRepository.findOne(aLong);
        if(movie.isPresent())
            return movie.get();
        throw new RuntimeException("Could not find movie by given ID.");
    }
}
