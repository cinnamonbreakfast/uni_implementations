package servlets;

import authentication.CredentialsManager;
import controller.GameController;
import controller.ProfilesController;
import controller.RequestController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/MovePiece")
public class MovePiece extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String parent = req.getParameter("parent");
        String piece = req.getParameter("piece");
        String target = req.getParameter("target");

        if(req.getSession().getAttribute("username") == null)
        {
            resp.getWriter().write("Not logged in");
            resp.addCookie(new Cookie("username", null));
        } else {
            GameController gameController = new GameController();
            System.out.println(username+ " has "+gameController.getMoves(username)+" moves left");

            gameController.decrementMoves(username);

            RequestController requestController = new RequestController();
            requestController.logRequest(username, "movePiece", true);

            resp.getWriter().write(gameController.movePiece(username, parent, piece, target));
        }
    }
}