package mat.unical.it.bookly.controller;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("http://localhost:4200")
public class Auth {
    @GetMapping("/checkAuth")
    public Boolean isAuth(HttpServletRequest req, String jsessionid){
        HttpSession session = (HttpSession) req.getServletContext().getAttribute(jsessionid);
        if (session.getAttribute("user") != null) {
            System.out.println("ciao");
            return true;
        }else {
            System.out.println("ciao admin");
            return false;
        }
    }
}
