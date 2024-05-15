package com.humber.Week1Test.controllers;

import com.humber.Week1Test.models.Dish;
import com.humber.Week1Test.repositories.DishRepo;

import com.humber.Week1Test.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // all methods inside returns JSON format
// #Controller returns the HTML doc // looks for HTML view templates folder


public class DishController {// has to be in a class spring  BEAN
    @Autowired
    private DishService dishService;

    // getting the resturant name from the app properities file
    @Value("${RESTAURANT}")
    String restaurantName;


    // get all dishes

    @GetMapping("/dishes")
    public List<Dish> getAllDishes() {
        return dishService.getAllDishes();
    }

    @GetMapping("/home")
    public String home() {
        return restaurantName;
    }






}
