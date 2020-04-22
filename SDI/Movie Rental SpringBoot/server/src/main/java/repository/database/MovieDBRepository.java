package repository.database;


import domain.Movie;
import domain.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class MovieDBRepository implements SortingRepository<Long, Movie> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private Validator<Movie> movieValidator;

    @Autowired
    private RowMapper<Movie> movieMapper;

    @Override
    public Iterable<Movie> findAll(Sort sort) {
        return sort.sort(
                StreamSupport.stream(
                        this.findAll().spliterator(),
                        false
                )
                        .collect(Collectors.toList()))
                .stream()
                .map((e)->(Movie)e)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Movie> findOne(Long aLong) {
        Movie movie = null;

        String sql = "select * from movie where id=?";
        movie = (Movie) jdbcOperations.queryForObject(sql, new Object[] { aLong }, movieMapper);

        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll() {
        String sql = "select * from movie";
        return jdbcOperations.query(sql, movieMapper);
    }

    @Override
    public Optional<Movie> save(Movie entity) throws IOException {
        movieValidator.validate(entity);

        String sql = "insert into movie(id, name, description, price, rating) values(?, ?, ?, ?, ?)";
        int affected_rows = jdbcOperations.update(sql,
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getRating()
        );

        Movie movie = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> delete(Long aLong) {
        String sql = "delete from movie where id=?";
        int affected_rows = jdbcOperations.update(sql, aLong);

        Movie movie = affected_rows > 0 ? new Movie("", "", 0, 0) : null;
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> update(Movie entity) {
        movieValidator.validate(entity);

        String sql = "update movie set name=?, description=?, price=?, rating=? where id=?";
        int affected_rows = jdbcOperations.update(sql,
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getRating(),
                entity.getId());

        Movie movie = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(movie);
    }




}
