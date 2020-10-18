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

@WebServlet("/NewGame")
public class NewGame extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");

        if(req.getSession().getAttribute("username") == null)
        {
            resp.getWriter().write("Not logged in");
            resp.addCookie(new Cookie("username", null));
        } else {
            RequestController requestController = new RequestController();
            requestController.logRequest(username, "newGame", true);

            System.out.println(username+": NO GAME, CREATE A NEW ONE");
            GameController gameController = new GameController();
            gameController.deleteGames(username);
            gameController.newGame(username);
            System.out.println("DELETED GAME");
            resp.getWriter().write("Done");
            System.out.println("DONE GAME");
        }
    }
}