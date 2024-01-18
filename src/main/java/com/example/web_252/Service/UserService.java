package com.example.web_252.Service;

import com.example.web_252.Models.Role;
import com.example.web_252.Models.User;
import com.example.web_252.Repositories.UserRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpSession httpSession;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }


    @Transactional
    public void toggleUserStatus(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        user.setActive(!user.isActive());
        userRepository.save(user);
    }

    public Long getCurrentUserId() {
        return (Long) httpSession.getAttribute("CURRENT_USER_ID");
    }
    public Set<Role> getUserRoles(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getRoles();
    }

}
