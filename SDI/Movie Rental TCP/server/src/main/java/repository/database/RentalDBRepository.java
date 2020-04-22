package repository.database;

import Domain.Rental;
import Domain.Validators.RentalValidator;
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

public class    RentalDBRepository implements SortingRepository<Long, Rental> {

    /*
    mereu unde ai affected rows, iei rezultatul din executie

    affected_rows = statement.executeUpdate();
     */
    private final Validator<Rental> validator;


    public RentalDBRepository(RentalValidator rv) {
        validator = rv;
    }

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
    public Optional<Rental> findOne(Long id)
    {
        Rental rental = null;

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement ps = db.conn.prepareStatement("select * from rental where id = ?");
            ps.setLong(1,id);
            ResultSet result = ps.executeQuery();

            result.next();

            rental = new Rental(result.getLong("client"), result.getLong("movie"));
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return Optional.ofNullable(rental);
    }

    public Iterable<Rental> findAll()
    {
        HashSet<Rental> rentals = new HashSet<>();
        try (DBConnection db = new DBConnection())
        {
            ResultSet result = db.conn.createStatement().executeQuery("select * from rental");
            while (result.next())
            {
                rentals.add(new Rental(result.getLong("client"), result.getLong("movie")));
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return rentals;
    }

    public Optional<Rental> save(Rental entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "insert into rental(client, movie) values(?, ?)"
            );
            statement.setLong(1, entity.getClient());
            statement.setLong(2, entity.getMovie());

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
        Rental rental = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(rental);
    }

    public Optional<Rental> delete(Long id)
    {
        // check its existance
        Optional<Rental> rental = findOne(id);

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "delete from rental where id = ?");
            statement.setLong(1, id);

            // get affected rows to return the status of the operation
            int affected_rows = statement.executeUpdate();

            if (affected_rows == 0) {
                rental = Optional.empty();
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return rental;
    }

    @Override
    public Optional<Rental> update(Rental entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;
        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement("update rental set client = ?, movie = ? where id = ?");
            statement.setLong(1, entity.getClient());
            statement.setLong(2, entity.getMovie());
            statement.setLong(3, entity.getId());


            affected_rows = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Rental rental = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(rental);
    }
}
