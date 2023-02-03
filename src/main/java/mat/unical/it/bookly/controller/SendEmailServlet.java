package mat.unical.it.bookly.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.Mail;
import mat.unical.it.bookly.persistance.model.Utente;

import javax.mail.MessagingException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@WebServlet("/doSendEmail")
public class SendEmailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Utente utente = null;
        String email = req.getParameter("email");

        utente = DBManager.getInstance().getUtenteDao().findByEmail(email);

        if(utente != null){
            String Token = utente.getResetPasswordToken();
            Mail mail = new Mail();
            mail.setupMailServerProperties();
            try {
                mail.draftEmail(Token,email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
            try {
                mail.sendEmail();
            } catch (MessagingException e) {
                e.printStackTrace();
            }

            resp.sendRedirect("/recupero_password.html");
        }else{
            resp.sendRedirect("/error_page.html");
        }

    }
}
