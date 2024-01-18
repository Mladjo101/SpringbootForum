package com.example.web_252;

import com.example.web_252.Models.Role;
import com.example.web_252.Models.User;
import com.example.web_252.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.EnumSet;

@Component
public class AdminSetup implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {

        String adminUsername = "admin";
        User existingAdmin = userRepository.findByUsername(adminUsername);

        if (existingAdmin == null) {

            User adminUser = new User();
            adminUser.setUsername(adminUsername);
            adminUser.setPassword("adminpass");
            adminUser.setRoles(EnumSet.of(Role.Admin));
            adminUser.setActive(true);

            userRepository.save(adminUser);
        }
    }
}
