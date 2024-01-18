package com.example.web_252.Service;

import com.example.web_252.Models.Comment;
import com.example.web_252.Repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getCommentsByDiscussion(Long discussionId) {
        return commentRepository.findByDiscussionId(discussionId);
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }
    public void deleteCommentsByDiscussionId(Long discussionId) {
        commentRepository.deleteByDiscussionId(discussionId);
    }


    public void deleteCommentById(Long commentId) {

        commentRepository.deleteById(commentId);
    }


}
