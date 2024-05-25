package com.humber.Week3ResturantApp.services;

import com.humber.Week3ResturantApp.models.Dish;
import com.humber.Week3ResturantApp.repositories.DishRepository;
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
