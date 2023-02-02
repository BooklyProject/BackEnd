package mat.unical.it.bookly.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/doLogout")
public class LogoutServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            session.removeAttribute("user");
        }
        else {
            session.removeAttribute("administrator");
        }

        session.invalidate();
        resp.sendRedirect("/");
    }
}
