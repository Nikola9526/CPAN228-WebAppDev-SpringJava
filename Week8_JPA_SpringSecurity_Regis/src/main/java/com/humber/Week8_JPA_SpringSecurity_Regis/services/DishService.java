package com.humber.Week8_JPA_SpringSecurity_Regis.services;

import com.humber.Week8_JPA_SpringSecurity_Regis.models.Dish;
import com.humber.Week8_JPA_SpringSecurity_Regis.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DishService {
    // Add a method to get all dishes
    // business logic
    private final DishRepository dishRepository;

    //constructor Injection
    @Autowired
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }


    // save a dish
    public int saveDish(Dish dish){
        if(dish.getPrice() <=20) {
            dishRepository.save(dish);
            return 1; // success response
        } // else
        return 0; // false failure
    }

    //delete a dish
    public int deleteDish(int id) {
        if(dishRepository.existsById(id)) {
            // if id exists go ahead with delete
            dishRepository.deleteById(id);
            return 1; //success
        }
        return 0; //fail
    }

    //update a dish
    public void updateDish(Dish dish ){
        //saves updated dish object fields back in database for same id where it all already exists
            dishRepository.save(dish);
    }

    // get a dish by id
    public Optional getDishById(int id){
        return dishRepository.findById(id); // but no .get() now
    }

    //pagination and Sorting Method
    public Page<Dish> getPaginatedDishes(int pageSize, int pageNo, String sortField, String sortDirection) {
        // sort the data based on the sort field and sort direction
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort); //the index starts at 0 so (num -1)
        return dishRepository.findAll(pageable);
        //returns page of dishes
    }

    public Page <Dish> getPaginatedDishesFiltered(int pageSize, int pageNo, String searchCategory, Double searchPrice) {
            Pageable pageable = PageRequest.of(pageNo-1, pageSize);
            return dishRepository.findByCategoryAndPrice(searchCategory, searchPrice, pageable);
    }
//save updated dish back into repo dish
}