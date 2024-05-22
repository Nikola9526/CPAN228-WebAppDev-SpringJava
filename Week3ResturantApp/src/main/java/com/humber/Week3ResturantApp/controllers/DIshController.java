package com.humber.Week3ResturantApp.controllers;

import com.humber.Week3ResturantApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController use this if interagteing with React then call GET, POST API
@Controller
@RequestMapping("/restaurant") // for api's localhost:8080 // for class

public class DIshController {

    // Field injection - injecting service into controller
    @Autowired // for injection field injection

    private  DishService dishService; // have to add @Service in DishService class making a BEAN

    @Value("${restaurant.name}")
    private String name;

    // Add a method to get all dishes


    // home page
    @GetMapping("/home")// for methods

    public String home(Model model){
        model.addAttribute("restaurantName", name); // two parameters
        return "home";
    }
    // will return JSON data (home only)



    @GetMapping("/dishes")
    public String getAllDishes(Model model){ // model makes it avabile to the view
        model.addAttribute("dishes", dishService.getAllDishes());
        return "menu";
        //return dishService.getAllDishes();  // return view
    }



}