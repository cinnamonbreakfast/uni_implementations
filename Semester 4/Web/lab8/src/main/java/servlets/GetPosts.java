package servlets;

import controller.GameController;
import controller.PostsController;
import controller.RequestController;
import model.Post;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/GetPosts")
public class GetPosts extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if(req.getSession().getAttribute("username") == null)
        {
            resp.getWriter().write("Not logged in");
            resp.addCookie(new Cookie("username", null));
        } else {
            PostsController postsController = new PostsController();


            List<Post> posts = postsController.getPosts();
            List<JSONObject> res = posts.stream().map(e-> {
                System.out.println(e.toString());
                try {
                    return new JSONObject(e.toString());
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            JSONArray jsArray = new JSONArray(res);
            System.out.println(jsArray.toString());
            resp.getWriter().write(jsArray.toString());
        }
    }
}