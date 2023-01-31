package mat.unical.it.bookly.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.SingleToken;
import mat.unical.it.bookly.persistance.model.Utente;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/doChangePassword")
public class ChangePasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String token = SingleToken.getInstance().getStringData();
        Utente utente = DBManager.getInstance().getUtenteDao().findByToken(token);
        String password = req.getParameter("password");
        String hashed = BCrypt.hashpw(password,BCrypt.gensalt(12));
        utente.setPassword(hashed
        );
        String random = String.valueOf(UUID.randomUUID());
        utente.setResetPasswordToken(random);
        DBManager.getInstance().getUtenteDao().saveOrUpdate(utente);
        resp.sendRedirect("/");
    }
}
