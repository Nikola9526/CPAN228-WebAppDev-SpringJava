package com.humber.FinalProject_userBlog.repositories;

import com.humber.FinalProject_userBlog.models.myUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface myUserRepository extends JpaRepository<myUser, Integer> {
    myUser findByUsername(String username);
}
