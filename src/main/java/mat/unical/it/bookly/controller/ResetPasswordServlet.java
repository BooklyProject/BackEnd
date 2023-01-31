package mat.unical.it.bookly.controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.SingleToken;
import mat.unical.it.bookly.persistance.model.Utente;

import java.io.IOException;

@WebServlet("/doResetPassword")
public class ResetPasswordServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String token = req.getParameter("token");
        Utente utente = DBManager.getInstance().getUtenteDao().findByToken(token);
        if (utente == null) {
            resp.sendRedirect("/error_page.html");
        }else{
            /*
            HttpSession session = req.getSession();
            session.setAttribute("token",token);

             */
            SingleToken.getInstance().setStringData(token);
            resp.sendRedirect("/reset_password_token.html");
        }
    }
}
