package servlets;

import controller.GameController;
import controller.RequestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Logout")
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestController requestController = new RequestController();
        requestController.logRequest(req.getSession().getAttribute("username").toString(), "logout", true);

        req.getSession().setAttribute("username", null);
        resp.addCookie(new Cookie("username", null));

        resp.sendRedirect("login.jsp");
    }
}