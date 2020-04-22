package repository.database;


import domain.Rental;
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

public class RentalDBRepository implements SortingRepository<Long, Rental> {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private Validator<Rental> rentalValidator;

    @Autowired
    private RowMapper<Rental> rentalMapper;

    @Override
    public Iterable<Rental> findAll(Sort sort) {
        return sort.sort(
                StreamSupport.stream(
                        this.findAll().spliterator(),
                        false
                )
                        .collect(Collectors.toList()))
                .stream()
                .map((e)->(Rental)e)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Rental> findOne(Long aLong) {
        Rental rental = null;

        String sql = "select * from rental where id=?";
        rental = (Rental) jdbcOperations.queryForObject(sql, new Object[] { aLong }, rentalMapper);

        return Optional.ofNullable(rental);
    }

    @Override
    public Iterable<Rental> findAll() {
        String sql = "select * from rental";
        return jdbcOperations.query(sql, rentalMapper);
    }

    @Override
    public Optional<Rental> save(Rental entity) throws IOException {
        rentalValidator.validate(entity);

        String sql = "insert into rental(id, client, movie) values(?, ?, ?)";
        int affected_rows = jdbcOperations.update(sql,
                entity.getId(),
                entity.getClient(),
                entity.getMovie()
        );

        Rental rental = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(rental);
    }

    @Override
    public Optional<Rental> delete(Long aLong) {
        String sql = "delete from rental where id=?";
        int affected_rows = jdbcOperations.update(sql, aLong);

        Rental rental = affected_rows > 0 ? new Rental(0L,0L) : null;
        return Optional.ofNullable(rental);
    }

    @Override
    public Optional<Rental> update(Rental entity) {
        rentalValidator.validate(entity);

        String sql = "update rental set client=?, movie=? where id=?";
        int affected_rows = jdbcOperations.update(sql,
                entity.getClient(),
                entity.getMovie(),
                entity.getId());

        Rental rental = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(rental);
    }
}
