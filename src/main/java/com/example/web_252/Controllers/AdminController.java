package com.example.web_252.Controllers;

import com.example.web_252.Models.Role;
import com.example.web_252.Repositories.DiscussionRepository;
import com.example.web_252.Service.CommentService;
import com.example.web_252.Service.DiscussionService;
import com.example.web_252.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private DiscussionRepository discussionRepository;

    //metoda za određivanje dashboarda
    @GetMapping("")
    public String adminPage(Model model) {

        var currentUserId = userService.getCurrentUserId();
        var currentUserRoles = userService.getUserRoles(currentUserId);
        if(currentUserRoles.contains(Role.Admin)){
            model.addAttribute("discussions", discussionService.getAllDiscussions());
            return "adminDisc";}
        else if(currentUserRoles.contains(Role.User))
        {
            model.addAttribute("discussions", discussionService.getAllDiscussions());
            return "discussion";}
        return "login";
    }
    //metoda za routanje na dashboard za admina
    @GetMapping("backdis")
    public String backToAdmin() {
        return "redirect:/admin";
    }

    //metoda koja vraća sve korisnike
    @GetMapping("/users")
    public String listUsers(Model model) {
        var currentUserId = userService.getCurrentUserId();
        var currentUserRoles = userService.getUserRoles(currentUserId);
        if(currentUserRoles.contains(Role.Admin)){
            model.addAttribute("users", userService.getAllUsers());
            return "pregledUsers";}  else if(currentUserRoles.contains(Role.User))
            return "discussion";
        return "login";
    }

    //metoda za promjenu statusa aktivan/neaktivan
    @GetMapping("/users/changeStatus/{id}")
    public String changeUserStatus(@PathVariable("id") Long userId) {
        userService.toggleUserStatus(userId);
        return "redirect:/admin/users";
    }

    //metoda koja vraća sve diskusije i određuje način prikaza po ulozi
    @GetMapping("/discussions")
    public String listDiscussions(Model model) {
        var currentUserId = userService.getCurrentUserId();
        var currentUserRoles = userService.getUserRoles(currentUserId);
        if(currentUserRoles.contains(Role.Admin)){
            model.addAttribute("discussions", discussionRepository.findAllWithComments());
            return "adminDisc";}
        else if(currentUserRoles.contains(Role.User))
        {
            model.addAttribute("discussions", discussionRepository.findAllWithComments());
            return "discussion";}
        return "login";
    }

    //dodavanje diskusije admin
    @GetMapping("/discussions-add")
    public String addDiscussions(Model model) {
        var currentUserId = userService.getCurrentUserId();
        var currentUserRoles = userService.getUserRoles(currentUserId);
        if(currentUserRoles.contains(Role.Admin)){
            model.addAttribute("discussions", discussionRepository.findAllWithComments());
            return "addDisc";} else if(currentUserRoles.contains(Role.User))
            return "discussion";
        return "login";
    }

    //brisanje diskusije samo admin
    @GetMapping("/discussions/delete/{id}")
    public String deleteDiscussion(@PathVariable Long id) {
        var currentUserId = userService.getCurrentUserId();
        var currentUserRoles = userService.getUserRoles(currentUserId);
        if(currentUserRoles.contains(Role.Admin)){
            discussionService.deleteDiscussion(id);
            return "redirect:/admin/discussions";}else if(currentUserRoles.contains(Role.User))
            return "discussion";
        return "login";

    }

}
