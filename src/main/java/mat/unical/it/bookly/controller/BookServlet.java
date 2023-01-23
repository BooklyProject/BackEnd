package mat.unical.it.bookly.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/getBook")
public class BookServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String [] sessionIdParam = req.getQueryString().split("&")[0].split("=");
        String sessionId = sessionIdParam[1];
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        System.out.println("libro: " + session.getAttribute("libro"));
        resp.sendRedirect("views/schedaLibro.html");
    }
}
