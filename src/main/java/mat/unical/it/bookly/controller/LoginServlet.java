package mat.unical.it.bookly.controller;


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

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException{
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        UtenteDao utenteDao = DBManager.getInstance().getUtenteDao();
        Utente utente = utenteDao.findByEmail(email);
        boolean logged;
        if(utente == null){
            logged = false;
        }else {
            if(BCrypt.checkpw(password,utente.getPassword())){
                logged = true;
                HttpSession session = req.getSession();
                session.setAttribute("user",utente);
                session.setAttribute("sessionId",session.getId());

                req.getServletContext().setAttribute(session.getId(),session);
            }else{
                logged = false;
            }
        }
        if(logged){
            resp.sendRedirect("/");
        }else{
            //RequestDispatcher dispatcher = req.getRequestDispatcher("views/error_page.html");
            //dispatcher.forward(req,resp);
            resp.sendRedirect("/error_page.html");
        }
    }

}
