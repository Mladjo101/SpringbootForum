package com.example.web_252.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "discussionId")
    private Discussion discussion;


    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

}
