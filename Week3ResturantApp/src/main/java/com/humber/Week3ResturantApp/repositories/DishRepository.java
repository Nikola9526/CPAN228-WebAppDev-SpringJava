package com.humber.Week3ResturantApp.repositories;
// importing Model Package Dish
import com.humber.Week3ResturantApp.models.Dish;

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
        dishes.add(
                Dish.builder().id(5).name("Mac and Cheese").category("Pasta").price(18.00).build()
        );
    }//static
    //add method that returns
    // method returns the added dishes so it can called from Service class
    public static List<Dish> getDishes() {
        return dishes;
    }
}// class
//repo done next service