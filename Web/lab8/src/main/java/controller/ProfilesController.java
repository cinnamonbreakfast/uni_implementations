package controller;

import db.Manager;
import model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfilesController {

    public User getTheUserWith(String username, String mother, String father) {
        String sql = "SELECT * FROM users WHERE username='" + username + "' AND mother='"+mother+"' AND father='"+father+"'";
        try {
            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
            ResultSet result = stmt.executeQuery();
            if (result.next()){
                User user = new User();
                user.setUsername(username);
                user.setFather(father);
                user.setMother(mother);
                return user;
            }
            return null;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    public List<User> search(String username,String name, String email, int age){
//        String sql = "SELECT * FROM profiles WHERE username<>'" + username + "'AND (name LIKE '%" + name + "%' OR email LIKE '%" + email + "%' OR AGE=" + age + " )";
//        try {
//            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
//            ResultSet result = stmt.executeQuery();
//            List<User> users = new ArrayList<>();
//
//            while (result.next()){
//                User user = new User();
//                user.setUsername(result.getString("username"));
//                user.setName(result.getString("name"));
//                user.setEmail(result.getString("email"));
//                user.setAge(result.getInt("age"));
//                users.add(user);
//            }
//            return users;
//
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//            return new ArrayList<>();
//        }
        return null;
    }

    public void update(User user) {
//        String sql = "UPDATE profiles SET email='" + user.getEmail() + "', name='" + user.getName() + "', age=" + user.getAge() + " WHERE username='" +
//                user.getUsername() + "';";
//        try {
//            PreparedStatement stmt = Manager.getConnection().prepareStatement(sql);
//            stmt.execute();
//            Manager.disconnect();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
    }
}