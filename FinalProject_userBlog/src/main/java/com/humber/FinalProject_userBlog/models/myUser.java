package com.humber.FinalProject_userBlog.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity

public class myUser {
    //fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fName;
    private String lName;
    private String username;
    private String email;

    @OneToMany(mappedBy = "myUser")
    private List<Post> posts;
    @OneToMany(mappedBy = "myUser")
    private List<Comment>comments;

    @ManyToMany
    @JoinTable(
            name="myuser_likes",
            joinColumns = @JoinColumn (name = "myUser_id"),
            inverseJoinColumns = @JoinColumn (name = "post_id"))
    private List<Post> likedPosts;


}
