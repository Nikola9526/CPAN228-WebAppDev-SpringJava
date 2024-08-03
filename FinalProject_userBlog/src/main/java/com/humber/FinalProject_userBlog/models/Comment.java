package com.humber.FinalProject_userBlog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity

public class Comment {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String content;
    @ManyToOne
    @JoinColumn (name = "post_id")
    private Post post;
    @ManyToOne
    @JoinColumn(name = "myUser_id")
    private myUser myUser;
}
