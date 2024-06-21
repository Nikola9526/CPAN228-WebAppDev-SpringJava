package com.humber.Lab6_Week7_JPA_SpringSecurity.controllers;

import com.humber.Lab6_Week7_JPA_SpringSecurity.models.Dish;
import com.humber.Lab6_Week7_JPA_SpringSecurity.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller// Auto Bean //so spring  knows it is controller
@RequestMapping("/restaurant/admin") // changed endpoint change all enpoints to this one
public class AdminController {

    private final DishService dishService;

    //constructor Injection
    @Autowired
    public AdminController(DishService dishService) {
        this.dishService= dishService;
    }

    @Value("${restaurant.name}")
    private String name;
// only 5 endpoints need Add (GET/POST), Update (GET/POST), Delete

    // add a method
    //GET /restaurant/add-dish
    @GetMapping("/add-dish") // Add a Dish Page "View- add-dish"
    public String addDish(Model model){
        model.addAttribute("dish", new Dish());
        return "admin/add-dish";
        // it is GET it just opens the form //one get to open page //one post to post data
    }

    //save the dish
    //POST
    @PostMapping("/add-dish")
    public String addDish(@ModelAttribute Dish dish, Model model){
        // saving in db
        int statusCode = dishService.saveDish(dish);
        // 1 for success, 0 for fail
        if (statusCode == 1){
            return "redirect:/restaurant/menu/1?message=dish added successfully!"; // so you do not need to repeat code
        } // else
        return "redirect:/restaurant/menu/1?errormessage=dish not added! Price is More Than 20!";
    }

    // delete a dish // need to change to GET cause html does not understand DELETE
    //@DeleteMapping("/delete/{id}") // this is path variable in {id} in curly braces
    @GetMapping("/delete/{id}") // this is path variable in {id} in curly braces
    public String deleteDish(@PathVariable int id){
        int deleteStatusCode = dishService.deleteDish(id);
        if(deleteStatusCode ==1) {
            return "redirect:/restaurant/menu/1?message=The Dish has been Deleted Successfully!";
        }
        return "redirect:/restaurant/menu/1?errormessage=Dish to be Deleted Does not exist!";
    }
    // update/change a dish
    //GET  /restaurant/update/{id} // Same Add a Dish Page // the form
    // but with different name depending on conditions if fields have values or not
    @GetMapping("/update/{id}")
    public String updateDish (Model model, @PathVariable int id) {
        Optional<Dish> dishToUpdate;
        dishToUpdate = dishService.getDishById(id);

        // showing values in form prefilled from existing data in database
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "admin/add-dish"; // change this name
    }
    //update a dish // the action
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Has Been Updated Successfully!";
    }

}
