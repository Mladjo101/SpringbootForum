package com.example.web_252.Service;

import com.example.web_252.Models.Comment;
import com.example.web_252.Models.Discussion;
import com.example.web_252.Repositories.DiscussionRepository;
import com.example.web_252.Repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    public List<Discussion> getAllDiscussions() {
        List<Discussion> discussions = discussionRepository.findAll();
        discussions.forEach(discussion -> {
            List<Comment> comments = commentRepository.findByDiscussionId(discussion.getId());
            discussion.setComments(new HashSet<>(comments));
        });
        return discussions;
    }
    public Optional<Discussion> getById(Long id){
        return discussionRepository.findById(id);
    }

    public void save(Discussion d){
        discussionRepository.save(d);
    }


    @Transactional
    public void deleteDiscussion(Long discussionId) {
        commentService.deleteCommentsByDiscussionId(discussionId);
        discussionRepository.deleteById(discussionId);
    }
    @Transactional
    public List<Discussion> getAllDiscussionsWithComments() {
        return discussionRepository.findAllWithComments();
    }
    public List<Discussion> getAllDiscussionsWithC() {
        return discussionRepository.findAll();
    }

}
