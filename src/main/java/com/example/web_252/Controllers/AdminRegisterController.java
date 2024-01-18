package com.example.web_252.Controllers;

import com.example.web_252.Models.User;
import com.example.web_252.Repositories.UserRepository;
import com.example.web_252.Service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminRegisterController {

    @Autowired
    RegisterService userService;

    //metoda za prikaz dodavanja usera od strane admina
    @GetMapping("/adminregister")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "adminRegister";
    }
    //dodavanje usera od strane admina
    @PostMapping("/adminregister")
    public String registerUser(@ModelAttribute User user) {
        userService.registerUser(user);
        return "redirect:/admin";
    }

}

