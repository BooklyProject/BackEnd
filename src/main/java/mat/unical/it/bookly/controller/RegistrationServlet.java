package mat.unical.it.bookly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Utente;

import java.io.IOException;

@WebServlet("/doRegistration")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("esecuzione registrazione");
        Utente u = new Utente();
        u.setEmail(req.getParameter("email"));
        u.setPassword(req.getParameter("password"));
        u.setCognome(req.getParameter("surname"));
        u.setNome(req.getParameter("name"));
        u.setUsername(req.getParameter("username"));
        u.setId(Long.valueOf(15));
        u.setUserImage("image");
        u.setBanned(true);
        DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
        HttpSession session = req.getSession();
        session.setAttribute("user",u);
        //resp.sendRedirect("/");
    }
}

