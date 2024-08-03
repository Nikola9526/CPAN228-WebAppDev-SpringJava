package com.humber.FinalProject_userBlog.services;

import com.humber.FinalProject_userBlog.models.Post;
import com.humber.FinalProject_userBlog.repositories.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    //constructor injection
    private final PostRepository postRepository;
    public PostService(PostRepository postRepository) {this.postRepository = postRepository;}
// CRUD operations
    // get all posts
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // find post by  id
    public Post getPostById(int id) {
        return postRepository.findById((long)id).orElse(null);
    }

    // Add a new post
    public void addPost(Post post) {
        Post existingPost = postRepository.findPostByTitle(post.getTitle());
        if (existingPost != null) {
            throw new IllegalArgumentException("Post " + post.getTitle() + " already exists");
        }
        // else if it is null
        //save it
        postRepository.save(post);
    }

    // update a already added post

    public void updatePost(int id,Post post) {
            boolean postExists = postRepository.existsById((long)id);
            if (!postExists) {
                // throw exception
                throw new IllegalArgumentException("Post with id  " + post.getId() + " does not exist");
            }
            post.setId(id);
            postRepository.save(post);
    }

    //delete a post
    public void deletePost(int id) {
        boolean postExists = postRepository.existsById((long)id);

        if (!postExists) {
            throw new IllegalArgumentException("Post with id  " + id + " does not exist");

        }

        postRepository.deleteById((long)id);
    }




}
