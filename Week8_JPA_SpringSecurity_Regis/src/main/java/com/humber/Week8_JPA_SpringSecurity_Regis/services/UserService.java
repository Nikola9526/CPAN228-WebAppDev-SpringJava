package com.humber.Week8_JPA_SpringSecurity_Regis.services;

import com.humber.Week8_JPA_SpringSecurity_Regis.models.myUser;
import com.humber.Week8_JPA_SpringSecurity_Regis.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // constructor injection // object of repo
    private final  UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //method to save the user information to the database(register)
    //0 = username already exists ; 1 =  successfully saved
    public int saveUser(myUser user) {
        //check if username already exists in the db
        if (userRepository.findByUsername(user.getUsername()).isPresent() ) {
            return 0;
        }

        //encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save thr user info to the db
        userRepository.save(user);
        return 1;
    }



}// class
