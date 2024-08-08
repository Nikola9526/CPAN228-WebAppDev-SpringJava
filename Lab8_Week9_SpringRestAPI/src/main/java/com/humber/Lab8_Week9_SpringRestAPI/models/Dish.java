package com.humber.Lab8_Week9_SpringRestAPI.models;

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
    // using .Auto means jpa responsible , Identity goes to the DB
    private int id;
    private String name;
    private double price;

    //embedded Cuisine Class to Dish
    @Embedded
    private Cuisine cuisine; // embed this

    //mapping Category to Dish (many to one) many dishes belong to one category
    @ManyToOne // establish the relationship
    @JoinColumn(name = "fk_category_id") // joining table with Dish, Category_Id
    // when you create/update  a dish, it will also save the category
    // when you delete a dish, it will not delete its category (this is what cascade does)
    @Cascade(CascadeType.PERSIST)
    private Category category;
}
// need FK