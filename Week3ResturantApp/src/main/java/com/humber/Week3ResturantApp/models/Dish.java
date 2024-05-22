package com.humber.Week3ResturantApp.models;

import lombok.*;
// For Created Model
@Data// form lombok makes getters/setters// creates everything
@NoArgsConstructor
@AllArgsConstructor
@Builder

//@Getter
//@Setter
public class Dish {

    //Fields
    private int id;
    private String name;
    private String category;
    private Double price;

//Write Repo First Bottom

}
