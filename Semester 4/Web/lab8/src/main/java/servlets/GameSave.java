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

@WebServlet("/GameSave")
public class GameSave extends HttpServlet {
    // todo: decrement on moves
    // todo: logout
    // todo: restart game

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if(req.getSession().getAttribute("username") == null)
        {
            resp.getWriter().write("Not logged in");
            resp.addCookie(new Cookie("username", null));
        } else {
            GameController gameController = new GameController();

            RequestController requestController = new RequestController();
            requestController.logRequest(username, "gameSave", true);

            resp.getWriter().write(gameController.gameSaved(username));
        }
    }
}