package com.example.web_252.Repositories;

import com.example.web_252.Models.Discussion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DiscussionRepository extends JpaRepository<Discussion, Long> {
    @Query("SELECT diskusija FROM Discussion diskusija LEFT JOIN FETCH diskusija.comments")
    List<Discussion> findAllWithComments();

}
