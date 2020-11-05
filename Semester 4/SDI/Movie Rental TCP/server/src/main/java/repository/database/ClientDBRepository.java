package repository.database;

import Domain.Client;
import Domain.Validators.ClientValidator;
import Domain.Validators.Validator;
import Domain.Validators.ValidatorException;
import repository.DBConnection;
import repository.SortingRepository;
import repository.sorting.Sort;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientDBRepository implements SortingRepository<Long, Client> {

    /*
    mereu unde ai affected rows, iei rezultatul din executie

    affected_rows = statement.executeUpdate();
     */
    private final Validator<Client> validator;


    public ClientDBRepository(ClientValidator cv) {
        validator = cv;
    }

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

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement ps = db.conn.prepareStatement("select * from client where id = ?");
            ps.setLong(1,id);
            ResultSet result = ps.executeQuery();

            result.next();

            client = new Client(result.getString("firstname"), result.getString("secondname"),result.getString("job"),result.getInt("age"));
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return Optional.ofNullable(client);
    }

    public Iterable<Client> findAll()
    {
        HashSet<Client> clients = new HashSet<>();
        try (DBConnection db = new DBConnection())
        {
            ResultSet result = db.conn.createStatement().executeQuery("select * from client");
            while (result.next())
            {
                clients.add(new Client(result.getString("firstname"), result.getString("secondname"),result.getString("job"),result.getInt("age")));
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return clients;
    }

    public Optional<Client> save(Client entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "insert into client(firstName, secondName, job, age) values(?, ?, ?, ?)"
            );
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getSecondName());
            statement.setString(3, entity.getJob());
            statement.setInt(4, entity.getAge());

            // check if changes has occured
            affected_rows = statement.executeUpdate();

            // get ID
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next())
            {
                long id = generatedKeys.getLong(1);
                entity.setId(id);
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        // return the state of the changes
        Client client = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(client);
    }

    public Optional<Client> delete(Long id)
    {
        // check its existance
        Optional<Client> client = findOne(id);

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "delete from client where id = ?");
            statement.setLong(1, id);

            // get affected rows to return the status of the operation
            int affected_rows = statement.executeUpdate();

            if (affected_rows == 0) {
                client = Optional.empty();
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public Optional<Client> update(Client entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;
        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement("update client set firstname = ?, secondname = ?, job = ?, age = ? where id = ?");
            statement.setString(1, entity.getFirstName());
            statement.setString(2, entity.getSecondName());
            statement.setString(3, entity.getJob());
            statement.setLong(4, entity.getAge());
            statement.setLong(5, entity.getId());


            affected_rows = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Client client = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(client);
    }
}
