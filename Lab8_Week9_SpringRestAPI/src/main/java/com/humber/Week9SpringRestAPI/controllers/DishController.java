package com.humber.Week9SpringRestAPI.controllers;

import com.humber.Week9SpringRestAPI.models.Category;
import com.humber.Week9SpringRestAPI.models.Dish;
import com.humber.Week9SpringRestAPI.services.CategoryService;
import com.humber.Week9SpringRestAPI.services.DishService;
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

    // create 5 Endpoints

    // getAllDishes
    @GetMapping("/dishes")
    public ResponseEntity<List<Dish>> getAllDishes() {
        return ResponseEntity.ok(dishService.getAllDishes());
//      recieve the list of dishes and the statues code
//      1. statues 2. headers 3. list of dishes
    }


    // get Dish By ID
    @GetMapping("/dishes/{id}")
    public ResponseEntity<Dish> getDishById(@PathVariable int id) {
        return ResponseEntity.ok(dishService.getDishById(id));
        // finding dish by its ID
    }
    // Request Param used
    // Path Variable used
    // request Body have not used

    // Add a Dish
    @PostMapping("/dishes")
    public ResponseEntity<String> addDish(@RequestBody Dish dish) { // contains payload
        // check if category exists
        Category existingCategory = categoryService.getCategoryByName(dish.getCategory().getName());
        // category's already exists
        if (existingCategory != null) {

            dish.setCategory(existingCategory);
            //dish.getCategory().setId
        }
        try {
            dishService.addDish(dish);
            // try to add dish
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
            // in postman Error space the message
            // this how it shows the result like view html from before
        }
        return ResponseEntity.ok("Dish added Successfully!");
    }
    // update a dish
    @PutMapping("/dishes/{id}")
    public ResponseEntity <String> updateDish(@PathVariable int id, @RequestBody Dish dish) {
        // check if category exists
        Category existingCategory = categoryService.getCategoryByName(dish.getCategory().getName());
        if (existingCategory != null) {
            dish.setCategory(existingCategory);
        }
        try{
            dishService.updateDish(id, dish);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
        return ResponseEntity.ok("Dish updated successfully!");
    }

    // delete Dish
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
