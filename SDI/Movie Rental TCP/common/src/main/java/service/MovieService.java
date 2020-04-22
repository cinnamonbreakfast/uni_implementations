package service;

import Domain.Movie;
import Domain.Validators.ValidatorException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface MovieService extends Service<Long, Movie> {
    CompletableFuture<Void> addMovie(Movie movie) throws ValidatorException, IOException;
    CompletableFuture<List<Movie>> getAllMovies(String... sort);
    CompletableFuture<List<Movie>> filterMovieByName(String s);
    CompletableFuture<List<Movie>> filterMovieByDescription(String d);
    CompletableFuture<List<Movie>> filterMovieByPrice(int p);
    CompletableFuture<List<Movie>> filterMovieByRating(int r);
    CompletableFuture<Void> removeMovie(Long id);
    CompletableFuture<Void> updateMovie(Movie entity);
}
