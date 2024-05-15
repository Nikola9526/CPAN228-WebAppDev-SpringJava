package com.humber.Week2ThymeLeafApp.repositories;
// importing Model Package Dish
import com.humber.Week2ThymeLeafApp.models.Dish;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    private static List<Dish> dishes = new ArrayList<>();

    static {
        //dishes.add(new Dish(1,"p","k",1.2)); // makeing dish object
        dishes.add(
                Dish.builder().id(1).name("Burger").category("Non-Veg").price(5.00)
                        .build()
        );
    }//static
}// class
