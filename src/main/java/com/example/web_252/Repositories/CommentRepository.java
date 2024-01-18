package com.example.web_252.Repositories;

import com.example.web_252.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByDiscussionId(Long discussionId);
    void deleteByDiscussionId(Long discussionId);

    void deleteByUserId(Long userId);
}
