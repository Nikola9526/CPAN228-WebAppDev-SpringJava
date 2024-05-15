package com.humber.Week2ThymeLeafApp.repositories;
// importing Model Package Dish
import com.humber.Week2ThymeLeafApp.models.Dish;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class DishRepository {

    //@Getter
    private static List<Dish> dishes = new ArrayList<>();

    static {
        //dishes.add(new Dish(1,"","",1.2)); // making dish object 1st way
        dishes.add(
                Dish.builder().id(1).name("Burger").category("Non-Veg").price(5.00)
                        .build()
                // we use builder pattern shows what the field is named

        );
        dishes.add(
                Dish.builder().id(2).name("Pizza").category("Sausage").price(15.00)
                        .build()
                // we use builder pattern shows what the field is named

        );
        dishes.add(
                Dish.builder().id(3).name("Poutine").category("Fires,Cheese, Gravy").price(3.00)
                        .build()
                // we use builder pattern shows what the field is named

        );
        dishes.add(
                Dish.builder().id(4).name("Meatballs").category("Meat").price(25.00)
                        .build()
                // we use builder pattern shows what the field is named

        );
    }//static

    //add method that returns


    public static List<Dish> getDishes() {
        return dishes;
    }
}// class
//repo done next service