package mat.unical.it.bookly.controller;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Amministratore;
import mat.unical.it.bookly.persistance.model.Utente;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.io.IOException;

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        Utente utente = DBManager.getInstance().getUtenteDao().findByEmail(email);
        Amministratore amministratore = DBManager.getInstance().getAmministratoreDao().findByEmail(email);
        boolean logged;
        if(utente == null && amministratore == null){
            logged = false;
        }else {
            if(utente == null && BCrypt.checkpw(password,amministratore.getPassword())) {
                logged = true;
                HttpSession session = req.getSession();
                session.setAttribute("user", null);
                session.setAttribute("administrator", amministratore);
                session.setAttribute("jsessionid", session.getId());
                req.getServletContext().setAttribute(session.getId(), session);
            }
            else if(utente != null && BCrypt.checkpw(password,utente.getPassword()) && utente.getBanned() != true){
                logged = true;
                HttpSession session = req.getSession();
                session.setAttribute("user", utente);
                session.setAttribute("administrator", null);
                session.setAttribute("jsessionid", session.getId());
                req.getServletContext().setAttribute(session.getId(), session);
            }
            else{
                logged = false;
            }
        }
        if(logged) {
            HttpSession session = req.getSession();
            System.out.println("sessionId: " + session.getId());
            resp.sendRedirect("http://localhost:4200/?jsessionid=" + session.getId());
        }
        else{
            resp.sendRedirect("/error_page.html");
        }
    }

}
