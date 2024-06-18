package com.humber.A2_Clothes_Warehouse_JPA_App.models;

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

public class Item {
    //Fields
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)

    private int id;

    private String name;
    //Brand needs Enumerated Type of String so the database stores them as strings, and it can read it back  as string
    @Enumerated (EnumType.STRING)
    private Brand brand;
    private int year_creation;
    private double price;

}
