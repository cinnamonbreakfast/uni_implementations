package controller;

import db.Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class RequestController {
    public int logRequest(String username, String method, boolean fullfilled)
    {
        int mod = 0;
        String result = "";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Manager.connect();
            con = Manager.getConnection();
            Statement stmt = con.createStatement();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO requests (user, method, fulfilled) values(?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, method);
            pstmt.setBoolean(3, fullfilled);
            mod = pstmt.executeUpdate();

            Manager.disconnect();
        } catch (SQLException e)  {
            e.printStackTrace();
            System.out.println(e);
        }

        return mod;
    }

    public int logUpdateRequest(String username, String method, boolean fullfilled)
    {
        int mod = 0;
        String result = "";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Manager.connect();
            con = Manager.getConnection();
            Statement stmt = con.createStatement();

            PreparedStatement pstmt = con.prepareStatement("INSERT INTO requests (user, method, fullfilled) values(?, ?, ?)");
            pstmt.setString(1, username);
            pstmt.setString(2, method);
            pstmt.setBoolean(3, fullfilled);
            mod = pstmt.executeUpdate();

            Manager.disconnect();
        } catch (SQLException e)  {
            e.printStackTrace();
            System.out.println(e);
        }

        return mod;
    }
}
