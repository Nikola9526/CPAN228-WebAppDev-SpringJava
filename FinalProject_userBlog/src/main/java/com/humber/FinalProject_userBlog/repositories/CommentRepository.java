package com.humber.FinalProject_userBlog.repositories;

import com.humber.FinalProject_userBlog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    public List<Comment> findByPostId(Integer postId);
}
