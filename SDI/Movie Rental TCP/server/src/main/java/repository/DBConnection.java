package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements AutoCloseable
{
    private String dbUrl = "jdbc:postgresql://localhost:5432/new_database?currentSchema=new_schema";
    private static final String USER = "postgres";
    private static final String PASSWORD = "radu1234";
    public Connection conn;

    public DBConnection() throws SQLException, ClassNotFoundException
    {
        //Class.forName("org.h2.Driver");
        conn = DriverManager.getConnection(dbUrl, USER, PASSWORD);
    }

    @Override
    public void close()
    {
        try
        {
            conn.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}