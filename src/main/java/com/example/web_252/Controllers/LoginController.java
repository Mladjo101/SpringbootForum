package com.example.web_252.Controllers;


import com.example.web_252.Models.Role;
import com.example.web_252.Models.User;
import com.example.web_252.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private HttpSession httpSession;

    //login screen
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    //logika za login
    @PostMapping("/login")
    public String processLogin(Model model, @RequestParam String username, @RequestParam String password) {
        User user = userRepository.findByUsername(username);

        if (user != null && user.getPassword().equals(password)) {
            if(!user.isActive()){
                model.addAttribute("error", "Login failed: This account has been deactivated");
                return "login";
            }
            httpSession.setAttribute("CURRENT_USER_ID", user.getId());

            if (user.getRoles().contains(Role.Admin)) {

                return "redirect:/admin";
            } else if (user.getRoles().contains(Role.User)) {

                return "redirect:/discussions";
            }
        }
        model.addAttribute("error", "Login failed: check your username and password");
        return "login";

    }
}
