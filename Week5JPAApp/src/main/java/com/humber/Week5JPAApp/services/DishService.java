package com.humber.Week5JPAApp.services;
import com.humber.Week5JPAApp.models.Dish;
import com.humber.Week5JPAApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DishService {
    // Add a method to get all dishes
    // business logic

    @Autowired
    private DishRepository dishRepository; //making object

    // need to inject DIshRepo Class but can not make object so use @Autowire for injection of repo class
    // get All Dishes
    public List<Dish> getAllDishes() {
        return dishRepository.getDishes();
        //return DishRepository.getDishes();
        //throwing error cause it is called as static, but it is not static
    }

    /*save a dish*/
    public int saveDish (Dish dish){
        if(dish.getPrice()<20){
            // save in db
            dishRepository.save(dish);
            return 1;
        } else {
            //stays on the same page does not redirect
            return 0;
        }
    }
}//Class