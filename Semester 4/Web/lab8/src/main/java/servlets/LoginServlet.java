package servlets;

import authentication.CredentialsManager;
import controller.ProfilesController;
import controller.RequestController;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String m = req.getParameter("mother");
        String f = req.getParameter("father");

        ProfilesController profilesController = new ProfilesController();
        User user = profilesController.getTheUserWith(username, m, f);

        if(user != null)
        {
            resp.getWriter().write(user.getFather());
        } else {
            resp.getWriter().write("error");
        }


//        HttpSession session=req.getSession();
//        session.setAttribute("username", username);
//        session.setMaxInactiveInterval(60*60*24);
//
//        Cookie userName = new Cookie("username", username);
//        resp.addCookie(userName);
//
//        resp.setContentType("text/plain");
//        resp.getWriter().write("login_ok");
    }
}