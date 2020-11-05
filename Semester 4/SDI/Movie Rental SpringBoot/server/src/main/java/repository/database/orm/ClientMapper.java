package repository.database.orm;

import domain.Client;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientMapper implements RowMapper<Client> {
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client(
                rs.getString("firstname"),
                rs.getString("secondname"),
                rs.getString("job"),
                rs.getInt("age"));
        client.setId(rs.getLong("id"));

        return client;
    }
}
