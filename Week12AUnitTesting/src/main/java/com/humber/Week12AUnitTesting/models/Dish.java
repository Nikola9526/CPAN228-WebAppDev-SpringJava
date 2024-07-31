package com.humber.Week12AUnitTesting.models;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;


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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // responsible for auto increment
    // using Auto jpa responsible , Identity goes to the DB
    private int id;
    private String name;
    private double price;

    //embedded Cuisine Class to Dish
    @Embedded
    private Cuisine cuisine; // embed this

    //mapping Category to Dish (many to one)
    @ManyToOne // establish the relationship
    @JoinColumn(name = "fk_category_id")
    // when you create/update  a dish, it will also save the category
    // when you delete a dish, it will not delete its category (this is what cascade does)
    @Cascade(CascadeType.ALL) // change to All instead was PERSIST
    private Category category;
}
// need FK