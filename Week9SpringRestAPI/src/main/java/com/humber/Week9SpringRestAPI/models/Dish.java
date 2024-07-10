package com.humber.Week9SpringRestAPI.models;

import jakarta.persistence.*;
import lombok.*;


//@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity // tell its the id, generates table

public class Dish {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // resonbile for auto increment
    // useing Auto jpa responitable , Identity goes to the DB
    private int id;
    private String name;
    private double price;

    //embedded Cuisine Class to Dish
    @Embedded
    private Cuisine cuisine; // embed this
}
