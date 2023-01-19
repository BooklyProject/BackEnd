package mat.unical.it.bookly.controller;

import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/doLogin")
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        System.out.println("esecuzione login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        System.out.println(email);
        UtenteDao utenteDao = DBManager.getInstance().getUtenteDao();
        Utente utente = utenteDao.findByEmail(email); //check se corretta
        boolean logged;
        if(utente == null){
            logged = false;
        }else {
            if(password.equals(utente.getPassword())){
                logged = true;
                HttpSession session = req.getSession();
                session.setAttribute("user",utente);
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
