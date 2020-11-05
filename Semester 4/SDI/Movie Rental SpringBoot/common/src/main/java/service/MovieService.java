package service;

import domain.Movie;
import domain.exceptions.ValidatorException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieService extends Service<Long, Movie> {
    void addMovie(Movie movie) throws ValidatorException, IOException;
    List<Movie> getAllMovies(String... sort);
    List<Movie> filterMovieByName(String s);
    List<Movie> filterMovieByDescription(String d);
    List<Movie> filterMovieByPrice(int p);
    List<Movie> filterMovieByRating(int r);
    void removeMovie(Long id);
    void updateMovie(Movie entity);
}
