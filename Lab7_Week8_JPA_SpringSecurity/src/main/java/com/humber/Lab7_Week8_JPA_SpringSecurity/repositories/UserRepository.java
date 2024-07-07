package com.humber.Lab7_Week8_JPA_SpringSecurity.repositories;

import com.humber.Lab7_Week8_JPA_SpringSecurity.models.myUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// extending already a bean no need to wirth @Repo
public interface UserRepository extends JpaRepository<myUser, Long> {

    //get optional user by username
    public Optional<myUser> findByUsername(String username);
    // now work with service ,//JPA smart engough no quireies needed it finds feild  username in model
}
