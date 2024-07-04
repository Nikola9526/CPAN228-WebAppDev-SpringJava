package com.humber.Week8_JPA_SpringSecurity_Regis.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class myUser {


    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    // will not work if username is blank empty
    @Column(nullable = false, unique = true)
    private String username;

    // 64 max characters, for password
    @Column(nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private String role;
}
