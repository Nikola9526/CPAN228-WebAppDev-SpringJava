package com.humber.FinalProject_userBlog.controllers;

import com.humber.FinalProject_userBlog.models.Post;
import com.humber.FinalProject_userBlog.services.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/socialPosts")
public class PostController {
    // constructor injection
    private final PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    // get all posts
    @GetMapping("/getAll")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }
    /*@PostMapping("/add")
    public ResponseEntity <String> createPost(@RequestBody Post post) {
        Post response = postService.addPost(post);


    }*/
}
