package controller;

import db.Manager;
import model.Post;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PostsController {

    public List<Post> getPosts() {
        List<Post> res = new ArrayList<>();

        ResultSet rs;
        Manager.connect();
        Connection con = Manager.getConnection();
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM posts");
            while(rs.next())
            {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                Post post = new Post(rs.getInt("id"), rs.getString("user"), rs.getInt("topicid"), rs.getString("text"), LocalDateTime.parse(rs.getString("datte").split("\\.")[0], formatter));
                res.add(post);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        Manager.disconnect();

        return res;
    }
}
