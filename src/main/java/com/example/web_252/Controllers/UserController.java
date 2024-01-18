package com.example.web_252.Controllers;

import com.example.web_252.Models.Comment;
import com.example.web_252.Models.Discussion;
import com.example.web_252.Models.User;
import com.example.web_252.Service.CommentService;
import com.example.web_252.Service.DiscussionService;
import com.example.web_252.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private DiscussionService discussionService;

    @Autowired
    private CommentService commentService;

    //vraća sve diskusije
    @GetMapping("/discussions")
    public String viewDiscussions(Model model) {
        model.addAttribute("discussions", discussionService.getAllDiscussions());
        return "discussion";
    }
    //dodavanje komentara
    @PostMapping("/user/discussions/{discussionId}/comment")
    public String addComment(@PathVariable Long discussionId, @RequestParam String content) {
        var userId = userService.getCurrentUserId();
        if(content.isEmpty())  return null;
        Optional<Discussion> discussion = discussionService.getById(discussionId);
        Optional<User> user = userService.getUserById(userId);

        if (discussion.isPresent() && user.isPresent()) {
            Comment newComment = new Comment();
            newComment.setContent(content);
            newComment.setDiscussion(discussion.get());
            newComment.setUser(user.get());
            commentService.addComment(newComment);
            return "redirect:/discussions";
        } else {

            throw new IllegalStateException("Not found");
        }
    }

}
