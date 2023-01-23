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
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/doRegistration")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Utente u = new Utente();
        u.setEmail(req.getParameter("email"));
        String password = req.getParameter("password");
        String hashed = BCrypt.hashpw(password,BCrypt.gensalt(12));
        u.setPassword(hashed);
        u.setCognome(req.getParameter("surname"));
        u.setNome(req.getParameter("name"));
        u.setUsername(req.getParameter("username"));
        u.setUserImage("image");
        u.setBanned(false);
        DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
        HttpSession session = req.getSession();
        session.setAttribute("user",u);
        resp.sendRedirect("/");
    }
}

