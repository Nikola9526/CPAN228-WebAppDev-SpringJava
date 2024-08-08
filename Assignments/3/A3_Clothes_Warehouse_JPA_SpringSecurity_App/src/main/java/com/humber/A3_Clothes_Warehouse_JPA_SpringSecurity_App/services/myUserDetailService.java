package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.myUser;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class myUserDetailService implements UserDetailsService {
    // constructor injection
    private final UserRepository userRepository;
    public myUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    // loadUser by username method fetches user details from the data source (user repo)
    // and transforms them into user details object that Spring Security can work with
    // for authentication and authorization

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<myUser> userOp = userRepository.findByUsername(username);

        if(userOp.isPresent()) {
            myUser user = userOp.get();
            // it gets it from user repo, builds object
            return User.builder()
                    // gets from DB the fields
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
