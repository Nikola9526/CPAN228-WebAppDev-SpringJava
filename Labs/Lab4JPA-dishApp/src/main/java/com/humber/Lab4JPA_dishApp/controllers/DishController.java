package com.humber.Lab4JPA_dishApp.controllers;

import com.humber.Lab4JPA_dishApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/restaurant") // for apis localhost:8080
public class DishController {

    private final DishService dishService;

    //constructor Injection
    @Autowired
    public DishController(DishService dishService) {
        this.dishService= dishService;
    }

    @Value("${restaurant.name}")
    private String name;

    //home-page
    //GET /restaurant/home
    @GetMapping("/home") //for methods
    public String home(Model model){
        model.addAttribute("restaurantName", name);
        return "home";
    }



}
