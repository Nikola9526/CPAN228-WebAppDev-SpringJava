package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.repositories;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.myUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface UserRepository extends JpaRepository<myUser, Long> {
     //get optional user by username, finds in DB
     public Optional<myUser> findByUsername (String username);
}
