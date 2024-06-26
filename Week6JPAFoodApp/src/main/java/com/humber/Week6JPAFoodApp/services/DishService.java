package com.humber.Week6JPAFoodApp.services;

import com.humber.Week6JPAFoodApp.models.Dish;
import com.humber.Week6JPAFoodApp.repositories.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    //get All Dishes
    public List<Dish> getAllDishes(){
        return  dishRepository.findAll();
    }


    // save a dish
    public int saveDish(Dish dish){
        if(dish.getPrice() <=20) {
            dishRepository.save(dish);
            return 1; // success response
        } // else
        return 0; // false failure
    }



    //get filtered dishes
    public List<Dish> getFilteredDishes ( String searchCategory, Double searchPrice) {
        if(searchCategory != null &&  searchPrice == null) {
            return dishRepository.findByCategory(searchCategory);

        }  else if(searchCategory == null && searchPrice != null) {
            dishRepository.findByPrice(searchPrice);
        }
            return dishRepository.findByCategoryAndPrice(searchCategory, searchPrice );


    }


    public List <Dish> getFilteredPrice( Double lower, Double upper) {
        return dishRepository.findByPriceBetween( lower, upper);
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



        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort); // the index starts at 0 so (num -1)
        return dishRepository.findAll(pageable);
        //returns page of dishes
    }

    // new
    public  Page<Dish> getDishesByCategory(String category, Pageable pageable){
        return dishRepository.findByCategory(category, pageable);
    }


}
        /*Optional<Dish> existingDishOptional = dishRepository.findById(dish.getId());
        if(existingDishOptional.isPresent()) {
            Dish existingDish =  existingDishOptional.get();

            // update
            existingDish.setName(dish.getName());
            existingDish.setPrice(dish.getPrice());
            existingDish.setCategory(dish.getCategory());

            // save back in repo db
            dishRepository.save(existingDish);
        } else {*/
//save updated dish back into repo dish