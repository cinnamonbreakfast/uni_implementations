package repository.database;

import Domain.Movie;
import Domain.Validators.MovieValidator;
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

public class MovieDBRepository implements SortingRepository<Long, Movie> {

    private final Validator<Movie> validator;


    public MovieDBRepository(MovieValidator cv) {
        validator = cv;
    }
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
    public Optional<Movie> findOne(Long id)
    {
        Movie movie = null;

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement ps = db.conn.prepareStatement("select * from movie where id = ?");
            ps.setLong(1,id);
            ResultSet result = ps.executeQuery();

            result.next();

            movie = new Movie(result.getString("name"), result.getString("description"),result.getInt("price"),result.getInt("rating"));
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return Optional.ofNullable(movie);
    }

    @Override
    public Iterable<Movie> findAll()
    {
        HashSet<Movie> movies = new HashSet<>();
        try (DBConnection db = new DBConnection())
        {
            ResultSet result = db.conn.createStatement().executeQuery("select * from movie");
            while (result.next())
            {
                movies.add(new Movie(result.getString("name"), result.getString("description"),result.getInt("price"),result.getInt("rating")));
            }
        } catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
        return movies;
    }

    @Override
    public Optional<Movie> save(Movie entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "insert into movie(name, description, price, rating) values(?, ?, ?, ?)"
            );
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPrice());
            statement.setInt(4, entity.getRating());

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
        Movie movie = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(movie);
    }

    @Override
    public Optional<Movie> delete(Long id)
    {
        // check its existance
        Optional<Movie> movie = findOne(id);

        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement(
                    "delete from movie where id = ?");
            statement.setLong(1, id);

            // get affected rows to return the status of the operation
            int affected_rows = statement.executeUpdate();

            if (affected_rows == 0) {
                movie = Optional.empty();
            }
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return movie;
    }


    @Override
    public Optional<Movie> update(Movie entity) throws ValidatorException
    {
        validator.validate(entity);

        int affected_rows = 0;
        try (DBConnection db = new DBConnection())
        {
            PreparedStatement statement = db.conn.prepareStatement("update movie set name = ?, description = ?, price = ?, rating = ? where id = ?");
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getPrice());
            statement.setLong(4, entity.getRating());
            statement.setLong(5, entity.getId());


            affected_rows = statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        Movie movie = affected_rows > 0 ? entity : null;
        return Optional.ofNullable(movie);
    }
}

