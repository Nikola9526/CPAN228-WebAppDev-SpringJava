package com.humber.Lab3JDBCApp_ResturantApp.controllers;

import com.humber.Lab3JDBCApp_ResturantApp.models.Dish;
import com.humber.Lab3JDBCApp_ResturantApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    //home-page
    @GetMapping("/home")// for methods
    public String home(Model model){
        model.addAttribute("restaurantName", name); // two parameters
        return "home";
    }
    // will return JSON data (home only)

    @GetMapping("/dishes")
    public String getAllDishes(Model model, @RequestParam (required = false) String message){ // model makes it available to the view
        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("message", message);
        // to pass dishes from back to front end
        return "menu";
        //return dishService.getAllDishes();  // return view
    }

    //add a method
    //GET
    // open up add a dish page (usually POST put get now)
    @GetMapping("/add-dish")// Add a Dish Page "View- add-dish"
    public String addDish(Model model ){
        model.addAttribute("dish", new Dish());
        return "add-dish";// that is the view .html
        // it is GET it just opens the form //one get to open page //one post to post data
    }

    // save the dish
    // use post // same end pont because one is get/post
    // POST
    @PostMapping("/add-dish")
    public String addDish( @ModelAttribute Dish dish, Model model){
        if(dishService.saveDish(dish) == 1){
            //redirect to menu and show added dish
            return "redirect:/restaurant/dishes?message=dish added successfully!"; // so you do not need to repeat code
        }   //else stay on add-dish page and show error message
            model.addAttribute("error", "Price must be Less than 20");
            return "add-dish";





        //saving in db
        /*dishService.saveDish(dish);*/
//        model.addAttribute("dishes", dishService.getAllDishes());
//        model.addAttribute("message", "Dish added Successfully");
//        model.addAttribute("dishes", dishService.getAllDishes()); //using DB you use this

        //model.addAttribute("dishes", dishService.getAllDishes() /*dish*/);
         //return "menu"; // return menu show what you added
        /*return "redirect:/restaurant/dishes?message=dish added successfully!"; // so you do not need to repeat code*/
    }
}//class