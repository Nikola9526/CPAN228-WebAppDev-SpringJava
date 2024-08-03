package com.humber.FinalProject_userBlog.services;

import com.humber.FinalProject_userBlog.models.myUser;
import com.humber.FinalProject_userBlog.repositories.myUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class myUserService {
    //constructor injection
    private final myUserRepository userRepository;
    public myUserService(myUserRepository userRepository) {this.userRepository = userRepository;}

    // to save a user
    public myUser saveUser (myUser myUser) {
        return this.userRepository.save(myUser);
    }

    public myUser findByUsername (String username) {
        return this.userRepository.findByUsername(username);
    }

    public List<myUser> findAllUsers() {
        return this.userRepository.findAll();
    }

}
