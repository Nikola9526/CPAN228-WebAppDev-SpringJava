package com.humber.Week2ThymeLeafApp.services;

import com.humber.Week2ThymeLeafApp.models.Dish;
import com.humber.Week2ThymeLeafApp.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {

    // Add a method to get all dishes

    public List<Dish> getAllDishes() {

        // get
        return DishRepository.getDishes();
    }

}//Class
