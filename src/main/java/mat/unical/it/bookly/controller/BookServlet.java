package mat.unical.it.bookly.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.model.Libro;
import mat.unical.it.bookly.persistance.model.Recensione;
import mat.unical.it.bookly.persistance.model.Utente;

import java.io.IOException;
import java.util.List;

@WebServlet("/getBook")
public class BookServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String [] sessionIdParam = req.getQueryString().split("&")[0].split("=");
        String sessionId = sessionIdParam[1];
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(sessionId);
        Utente user = (Utente) session.getAttribute("user");
        Libro libro = (Libro) session.getAttribute("libro");
        System.out.println("libro: " + libro);
        List<Recensione> recensioni =  DBManager.getInstance().getRecensioneDao().findReviewsByBook(user.getId(), libro.getIsbn());
        session.setAttribute("recensioni", recensioni);
        Long idPrimaRecensione = null;
        if(!recensioni.isEmpty()) {
            idPrimaRecensione = DBManager.getInstance().getRecensioneDao().findUserByReview(recensioni.get(0).getId());
        }
        session.setAttribute("idUtentePrimaRecensione", idPrimaRecensione);
        resp.sendRedirect("views/schedaLibro.html");
    }
}
