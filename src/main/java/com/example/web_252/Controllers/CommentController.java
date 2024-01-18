package com.example.web_252.Controllers;

import com.example.web_252.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    //brisanje komentara na diskusiji
    @PostMapping("/delete")
    public String deleteComment(@RequestParam Long commentId, @RequestParam Long discussionId) {
        commentService.deleteCommentById(commentId);

        // Redirect back to the discussion page after deleting the comment
        return "redirect:/admin";
    }
}
