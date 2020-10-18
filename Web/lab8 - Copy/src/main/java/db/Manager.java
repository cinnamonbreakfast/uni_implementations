package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Manager {
    private static Connection connection;

    public static void connect() {
        if(connection == null) {
            String url = "jdbc:mysql://127.0.0.1:3306/web";
            try{
                Class.forName( "com.mysql.jdbc.Driver" );
                connection = DriverManager.getConnection( url, "root", "" );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        connection = null;
    }

    public static Connection getConnection() {
        if(connection == null)
            connect();
        return connection;
    }
}