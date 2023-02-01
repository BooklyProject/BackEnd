package mat.unical.it.bookly.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import mat.unical.it.bookly.persistance.DBManager;
import mat.unical.it.bookly.persistance.dao.UtenteDao;
import mat.unical.it.bookly.persistance.model.Utente;

import org.apache.commons.io.IOUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@MultipartConfig
@WebServlet("/doRegistration")
public class RegistrationServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        if(DBManager.getInstance().getUtenteDao().findByEmail(req.getParameter("email")) == null && DBManager.getInstance().getAmministratoreDao().findByEmail("email") == null) {
            Utente u = new Utente();

            u.setEmail(req.getParameter("email"));
            String password = req.getParameter("password");
            u.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(12)));

            u.setNome(req.getParameter("name"));
            u.setCognome(req.getParameter("surname"));
            u.setUsername(req.getParameter("username"));
            u.setBanned(false);

            Part filePart = req.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            byte[] bytes = IOUtils.toByteArray(fileContent);
            String image = Base64.getEncoder().encodeToString(bytes);
            u.setUserImage(image);

            //Generazione token per il recupero della password
            String random = String.valueOf(UUID.randomUUID());
            u.setResetPasswordToken(random);

            DBManager.getInstance().getUtenteDao().saveOrUpdate(u);
            HttpSession session = req.getSession();
            session.setAttribute("user", u);
            resp.sendRedirect("/");
        }
        else{
            resp.sendRedirect("/error_page.html");
        }
    }
}

