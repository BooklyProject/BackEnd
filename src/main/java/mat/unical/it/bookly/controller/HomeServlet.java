package mat.unical.it.bookly.controller;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import mat.unical.it.bookly.persistance.model.Utente;

import java.io.IOException;

//@WebServlet("")
public class HomeServlet extends HttpServlet{

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Utente u = (Utente)session.getAttribute("user");
        if(u!=null){
            req.setAttribute("user",u);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("views/index.html");
        dispatcher.forward(req,resp);
    }

}