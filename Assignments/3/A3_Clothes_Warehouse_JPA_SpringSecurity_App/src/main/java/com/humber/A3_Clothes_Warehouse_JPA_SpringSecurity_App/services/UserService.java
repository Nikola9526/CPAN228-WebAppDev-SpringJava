package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.myUser;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // constructor injection // object of repo
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder)
    { this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;}

    // method to save user info in database (registration)
    // 0 = fail exists, 1= good
    public int saveUser(myUser user) {
        // check if username already exists in DB
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return 0;
        }
        //username does not exist
        // encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //save user info 1.username 2. encoded password in DB
        userRepository.save(user);
        return 1;
    }




}
