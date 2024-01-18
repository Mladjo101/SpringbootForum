package com.example.web_252.Service;

import com.example.web_252.Models.Role;
import com.example.web_252.Models.User;
import com.example.web_252.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;

@Service
public class RegisterService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;
    public void registerUser(User user) {

        User existingUser = userRepository.findByUsername(user.getUsername());
        if (existingUser != null) {

            throw new IllegalStateException("Username already taken");
        }
        user.setActive(true);
        user.setRoles(EnumSet.of(Role.User));//
        userRepository.save(user);
    }
}
