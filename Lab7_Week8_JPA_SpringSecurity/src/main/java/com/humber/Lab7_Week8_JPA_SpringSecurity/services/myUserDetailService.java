package com.humber.Lab7_Week8_JPA_SpringSecurity.services;

import com.humber.Lab7_Week8_JPA_SpringSecurity.models.myUser;
import com.humber.Lab7_Week8_JPA_SpringSecurity.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service // make the class a bean // so we can inject it
public class myUserDetailService implements UserDetailsService {

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

        if (userOp.isPresent()) {
            myUser user = userOp.get();
            // builds user object
            return User.builder()
                    // gets from database
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRole())
                    .build();
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }

    }
}
