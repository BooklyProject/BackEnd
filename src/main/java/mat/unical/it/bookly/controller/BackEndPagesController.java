package mat.unical.it.bookly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BackEndPagesController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }


    @GetMapping ("/register")
    public String register() {
        return "signUp";
    }
}
