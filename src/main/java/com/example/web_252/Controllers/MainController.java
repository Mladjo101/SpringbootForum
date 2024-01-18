package com.example.web_252.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    //osnovne rute za localhost:8080
    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }
    @GetMapping("/dashboard")
    public String redirectToDashboard() {
        return "redirect:/discussions";
    }
}
