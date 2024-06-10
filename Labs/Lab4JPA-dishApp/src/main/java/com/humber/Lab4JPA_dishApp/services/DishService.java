package com.humber.Lab4JPA_dishApp.services;

import com.humber.Lab4JPA_dishApp.models.Dish;
import com.humber.Lab4JPA_dishApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    // Add a method to get all dishes
    // business logic
    private final DishRepository dishRepository;

    // constructor Injection
    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    // get All Dishes
    public List<Dish> getAllDishes(){
        return dishRepository.findAll();
    }

    // save a dish
    public int saveDish(Dish dish){
        if(dish.getPrice() <=20) {
            dishRepository.save(dish);
            return 1; // success response
        } // else
        return 0; // false failure
    }



}
