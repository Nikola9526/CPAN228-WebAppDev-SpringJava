package com.humber.Week1Test.repositories;

import com.humber.Week1Test.models.Dish;
import java.util.ArrayList;
import java.util.List;

public class DishRepo {
    private static List<Dish> dishes = new ArrayList<>();

    // anything in static is the first thing being called
    static {
        dishes.add(new Dish(1, "Cheese", 3.0, "French"));
        dishes.add(new Dish(2,"Burger", 5.0, "American"));
        dishes.add(new Dish(3,"Sausage", 10.0, "German"));
    }

    public static List<Dish> getDishes() {
        return dishes;
    }
}
