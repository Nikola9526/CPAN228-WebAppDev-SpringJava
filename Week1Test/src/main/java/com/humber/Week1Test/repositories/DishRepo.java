package com.humber.Week1Test.repositories;

import com.humber.Week1Test.models.Dish;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository // turns into BEAN
public class DishRepo {
    private static List<Dish> dishes = new ArrayList<>();

    // anything in static is the first thing being called
    static {
        dishes.add(new Dish(1, "Cheese", 3.0, "French"));
        dishes.add(new Dish(2,"Burger", 5.0, "American"));
        dishes.add(new Dish(3,"Sausage", 10.0, "German"));
    }
    //@Bean// not for static
    public static List<Dish> getDishes() {
        return dishes;
    }
}
