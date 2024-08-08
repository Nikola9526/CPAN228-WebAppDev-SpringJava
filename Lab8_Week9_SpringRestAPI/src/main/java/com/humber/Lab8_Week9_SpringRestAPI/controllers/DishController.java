package com.humber.Lab8_Week9_SpringRestAPI.controllers;

import com.humber.Lab8_Week9_SpringRestAPI.models.Category;
import com.humber.Lab8_Week9_SpringRestAPI.models.Dish;
import com.humber.Lab8_Week9_SpringRestAPI.services.CategoryService;
import com.humber.Lab8_Week9_SpringRestAPI.services.DishService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurant/api")
public class DishController {
    //constructor injection
    private final DishService dishService;
    private final CategoryService categoryService;

    public DishController(DishService dishService, CategoryService categoryService) {
        this.dishService = dishService;
        this.categoryService = categoryService;
    }

    //create 5 Endpoints to getAll, get by ID, Add a Dish, Update Dish, Delete Dish

    // getAllDishes
    //GET
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
//      receive the list of dishes and the statues code
//      1. statues 2. headers 3. list of dishes
    }
    // get Dish By ID
    //GET
    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id) {
        return ResponseEntity.ok(dishService.getDishById(id));
        // finding dish by its ID
    }
    // Request Param used
    // Path Variable used
    // request Body have not used

    // Add a Dish
    //POST
    @PostMapping("/dishes")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) { // contains payload
        // check if category exists
        Category existingCategory = categoryService.getCategoryByName(dish.getCategory().getName());

        // if category's already exists
        if (existingCategory != null) {
            // set the category with the existing one
            dish.setCategory(existingCategory);
        }
        try {
            dishService.addDish(dish);
            // try to add dish
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
            // in postman Error space the message
            // this how it shows the result like view html from before
        }
        // it works, its added shows success statues
        return ResponseEntity.ok("Dish added Successfully!");
    }
    // update a dish //PUT
    @PutMapping("/dishes/{id}")
    public ResponseEntity <String> updateDish(@PathVariable int id, @RequestBody Dish dish) {
        // check if category exists
        Category existingCategory = categoryService.getCategoryByName(dish.getCategory().getName());
        if (existingCategory != null) {
            // if category exists put found category as category name
            dish.setCategory(existingCategory);
        }
        try{
            dishService.updateDish(id, dish);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Dish updated successfully!");
        // success response performs action
    }
    // delete Dish  //DELETE
    @DeleteMapping("/dishes/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable int id) {
        try{
            dishService.deleteDish(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Dish deleted successfully!");
    }
}
