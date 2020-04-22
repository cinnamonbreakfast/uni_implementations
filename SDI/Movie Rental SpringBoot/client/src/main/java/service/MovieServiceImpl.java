package service;

import domain.Movie;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.List;

public class MovieServiceImpl implements MovieService {

    @Autowired
    MovieService movieService;

    @Override
    public void addMovie(Movie entity) throws ValidatorException, IOException {
        movieService.addMovie(entity);
    }

    @Override
    public List<Movie> getAllMovies(String... sort) {
        return movieService.getAllMovies(sort);
    }

    @Override
    public List<Movie> filterMovieByName(String name) {
        return movieService.filterMovieByName(name);
    }

    @Override
    public List<Movie> filterMovieByDescription(String d) {
        return movieService.filterMovieByDescription(d);
    }

    @Override
    public List<Movie> filterMovieByPrice(int p) {
        return movieService.filterMovieByPrice(p);
    }

    @Override
    public List<Movie> filterMovieByRating(int r) {
        return movieService.filterMovieByRating(r);
    }

    @Override
    public void removeMovie(Long id) {
        movieService.removeMovie(id);
    }

    @Override
    public void updateMovie(Movie entity) {
        movieService.updateMovie(entity);
    }

    @Override
    public List<Movie> getAll() {
        return movieService.getAll();
    }

    @Override
    public List<Movie> getAllSortedAscendingByFields(String... fields) {
        return movieService.getAllSortedAscendingByFields(fields);
    }

    @Override
    public List<Movie> getAllSortedDescendingByFields(String... fields) {
        return movieService.getAllSortedDescendingByFields(fields);
    }

    @Override
    public Movie getByID(Long aLong) {
        return movieService.getByID(aLong);
    }
}
