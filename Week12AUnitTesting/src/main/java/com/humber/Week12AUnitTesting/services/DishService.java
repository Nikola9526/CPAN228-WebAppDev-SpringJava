package com.humber.Week12AUnitTesting.services;

import com.humber.Week12AUnitTesting.models.Dish;
import com.humber.Week12AUnitTesting.repositories.DishRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishService {
    //constructor injection
    private final DishRepository dishRepository;
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }
    // 5 apis, 5 methods need

    // getAll Dishes
    public List<Dish> getAllDishes() {
        return dishRepository.findAll();
    }

    // GetDish by ID
    public Dish getDishById(int id) {
        return dishRepository.findById((long) id).orElse(null);
    }

    // Add Dish
    public void addDish(Dish dish) {
        Dish existingDish =dishRepository.findDishByName(dish.getName());
        if (existingDish != null) {
            throw new IllegalArgumentException("Dish with name " + dish.getName() + " already exists");
        }
        // else it is null, no exist
        // it saves all entered dish info to the DB
        dishRepository.save(dish);
    }


    //Update Dish
    public void updateDish(int dishId, Dish dish) {
        boolean dishExists  = dishRepository.existsById((long) dishId);
        if (!dishExists) {
            // throw exception, if does not exist (can not update dish if it does not exist)
            throw new IllegalArgumentException("Dish with id " + dishId + " does not exist");
        }
        dish.setId(dishId);
        dishRepository.save(dish);
    }

    //Delete Dish
    public void deleteDish(int dishId) {
        boolean dishExists  =dishRepository.existsById((long) dishId);

        if (!dishExists) {
            // throw exception, if does not exist (can not update dish if it does not exist)
            throw new IllegalArgumentException("Dish with id " + dishId + " does not exist");

        }
        dishRepository.deleteById((long) dishId);
    }

    // can include things category, not working with bidirectional

}
