package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models;

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

    @Column( nullable = false,unique =true)
    private String username;

    @Column( nullable = false, length = 64)
    private String password;

    @Column(nullable = false)
    private String role;
}
