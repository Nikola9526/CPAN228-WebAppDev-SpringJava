package com.humber.Lab4JPA_dishApp.controllers;

import com.humber.Lab4JPA_dishApp.models.Dish;
import com.humber.Lab4JPA_dishApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    // menu page
    //GET /restaurant/menu
    @GetMapping("/menu")
    public String getAllDishes(Model model,
                               @RequestParam (required=false) String message,
                               @RequestParam (required = false) String errormessage,
                               @RequestParam (required = false) String searchCategory,
                               @RequestParam (required = false) Double searchPrice){
        if (searchCategory != null && searchPrice != null){
            // return back filtered dishes from the service layer
            List<Dish> filteredDishes = dishService.getFilteredDishes( searchCategory, searchPrice);
            model.addAttribute ("dishes", filteredDishes.isEmpty()? dishService.getAllDishes() : filteredDishes );
            model.addAttribute("message", filteredDishes.isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
            return "menu";
        }


        model.addAttribute("dishes", dishService.getAllDishes());
        model.addAttribute("message", message);
        model.addAttribute("errormessage", errormessage);

       // to pass dishes from back to front end
       return "menu";
    }

    // add a method
    //GET /restaurant/add-dish
    @GetMapping("/add-dish") // Add a Dish Page "View- add-dish"
    public String addDish(Model model){
        model.addAttribute("dish", new Dish());
        return "add-dish";
        // it is GET it just opens the form //one get to open page //one post to post data
    }

    // save the dish
    //POST
    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model){
        // saving in db
        int statusCode = dishService.saveDish(dish);
        // 1 for success, 0 for fail
        if (statusCode == 1){
            return "redirect:/restaurant/menu?message=dish added successfully!"; // so you do not need to repeat code
        } // else
        return "redirect:/restaurant/menu?errormessage=dish not added!";
    }

    // delete a dish // need to change to GET cause html does not understand DELETE
    //@DeleteMapping("/delete/{id}") // this is path variable in {id} in curly braces
    @GetMapping("/delete/{id}") // this is path variable in {id} in curly braces
    public String deleteDish(@PathVariable int id){
        int deleteStatusCode = dishService.deleteDish(id);
        if(deleteStatusCode ==1) {
            return "redirect:/restaurant/menu?message=dish deleted successfully!";
        }
        return "redirect:/restaurant/menu?message=Dish to be Deleted Does not exsit!";
    }

    @GetMapping("/update/{id}")
    public String updateDish (Model model, @PathVariable int id) {
        Optional<Dish> dishToUpdate;
        dishToUpdate = dishService.getDishById(id);
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "add-dish";
    }

    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);

        return "redirect:/restaurant/menu?message=dish updated successfully!";
    }




}
