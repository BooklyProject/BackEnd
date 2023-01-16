package mat.unical.it.bookly.controller;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class restController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/siumm")
    public String sium() {
        return "sium";
    }
}
