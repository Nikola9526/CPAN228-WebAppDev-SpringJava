package com.humber.Week1Test.services;

import com.humber.Week1Test.models.Dish;
import com.humber.Week1Test.repositories.DishRepo;
import org.springframework.stereotype.Service;

import java.util.List;

// business logic
@Service

public class DishService {

    // get all dishes
    public List<Dish> getAllDishes(){
        return DishRepo.getDishes();
    }


}
