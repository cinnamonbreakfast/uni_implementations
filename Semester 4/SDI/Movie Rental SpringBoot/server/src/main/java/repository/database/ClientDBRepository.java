package repository.database;

import domain.Client;
import domain.validators.Validator;
import domain.exceptions.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientDBRepository implements SortingRepository<Long, Client> {
    @Autowired
    private JdbcOperations jdbcOperations;

    @Autowired
    private Validator<Client> clientValidator;

    @Autowired
    private RowMapper<Client> clientMapper;

    //public ClientDBRepository(ClientValidator cv) {
    //validator = cv;
    //}

    @Override
    public Iterable<Client> findAll(Sort sort) {
        return sort.sort(
                StreamSupport.stream(
                        this.findAll().spliterator(),
                        false
                )
                        .collect(Collectors.toList()))
                .stream()
                .map((e)->(Client)e)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Client> findOne(Long id)
    {
        Client client = null;

        String sql = "select * from client where id=?";
        client = (Client) jdbcOperations.queryForObject(sql, new Object[] { id }, clientMapper);

        return Optional.ofNullable(client);
    }

    public Iterable<Client> findAll()
    {
        String sql = "select * from client";
        return jdbcOperations.query(sql, clientMapper);
    }

    public Optional<Client> save(Client entity) throws ValidatorException
    {
        clientValidator.validate(entity);

        String sql = "insert into client(id, firstname, secondname, job, age) values(?, ?, ?, ?, ?)";
        int affected_rows = jdbcOperations.update(sql,
                entity.getId(),
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getJob(),
                entity.getAge()
                );

        Client client = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(client);
    }

    public Optional<Client> delete(Long id)
    {
        String sql = "delete from client where id=?";
        int affected_rows = jdbcOperations.update(sql, id);

        Client client = affected_rows > 0 ? new Client("", "", "", 0) : null;
        return Optional.ofNullable(client);
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException
    {
        clientValidator.validate(entity);

        String sql = "update client set firstname=?, secondname=?, job=?, age=? where id=?";
        int affected_rows = jdbcOperations.update(sql,
                entity.getFirstName(),
                entity.getSecondName(),
                entity.getJob(),
                entity.getAge(),
                entity.getId());

        Client client = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(client);
    }
}
