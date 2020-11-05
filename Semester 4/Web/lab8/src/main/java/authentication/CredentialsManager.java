package authentication;

import db.Manager;
import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

public class CredentialsManager {

    public static String authenticate(String username, String password) {
        ResultSet rs;
        String result = "error";
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("select * from profiles where username='"+username+"' and password='"+(password)+"'");
            if (rs.next()) {
                result = "success";
            }
            rs.close();
            Manager.disconnect();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
     */
    public static String encrypt(String password){
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(password.getBytes());
            //Get the hash's bytes
            byte[] bytes = md.digest();
            //This bytes[] has bytes in decimal format;
            //Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public static void register(User user, String password){
//        String sql = "INSERT INTO profiles(username, password, email, picture, age, hometown, name) VALUES ('" +
//                user.getUsername() + "', '" + encrypt(password) + "', '" + user.getEmail()  + "', " +
//                + user.getAge() + ", '"  + user.getName() + "');";
//        try {
//            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
//            stmt.execute();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }

    }

}