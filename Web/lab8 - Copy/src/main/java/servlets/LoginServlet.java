package servlets;

import authentication.CredentialsManager;
import controller.ProfilesController;
import controller.RequestController;

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
        String password = req.getParameter("password");

        String authentication = CredentialsManager.authenticate(username,password);
        if(authentication.equals("success")){
            login(req.getSession(), username);

            HttpSession session=req.getSession();
            session.setAttribute("username", username);
            session.setMaxInactiveInterval(60*60*24);

            Cookie userName = new Cookie("username", username);
            resp.addCookie(userName);

            RequestController requestController = new RequestController();
            requestController.logRequest(username, "login", true);

            resp.setContentType("text/plain");
            resp.getWriter().write("login_ok");
        }
        else {
            resp.setContentType("text/plain");
            resp.getWriter().write("login_bad");
        }
    }

    private void login(HttpSession session, String username) {
        session.setAttribute("user", ProfilesController.getUserWithUsername(username));
    }
}