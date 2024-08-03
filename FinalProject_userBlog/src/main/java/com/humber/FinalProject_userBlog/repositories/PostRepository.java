package com.humber.FinalProject_userBlog.repositories;

import com.humber.FinalProject_userBlog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findPostByTitle(String title);
}
