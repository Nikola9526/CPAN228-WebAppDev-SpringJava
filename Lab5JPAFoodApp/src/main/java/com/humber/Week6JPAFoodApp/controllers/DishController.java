package com.humber.Week6JPAFoodApp.controllers;

import com.humber.Week6JPAFoodApp.models.Dish;
import com.humber.Week6JPAFoodApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Value("${page.size}")
    private int pageSize;

    //home-page
    //GET /restaurant/home
    @GetMapping("/home") //for methods
    public String home(Model model){
        model.addAttribute("restaurantName", name);
        return "home";
    }

    // menu page
    //GET /restaurant/menu
    @GetMapping("/menu/{pageNo}") // @PathVariable
    public String getAllDishes(Model model,
                               @RequestParam (required=false) String message,
                               @RequestParam (required = false) String errormessage,
                               @RequestParam (required = false) String searchCategory,
                               //@RequestParam (required = false) Double searchPrice,
                               @PathVariable int pageNo,
                               @RequestParam (required = false) String sortField,
                               @RequestParam (required = false) String sortDirection){ // has to be false so first click on menu it does not sort

        // give default values to sort field and sort direction
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        /*if ( searchCategory == null && searchPrice != null){
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchCategory,  searchPrice);
            model.addAttribute("dishes", filteredDishes.isEmpty()? dishService.getAllDishes() : filteredDishes );
            model.addAttribute("message", filteredDishes.isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
            return "menu";

        }  else if (searchCategory != null && searchPrice == null) {
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchCategory,  searchPrice);
            model.addAttribute("dishes", filteredDishes.isEmpty()? dishService.getAllDishes() : filteredDishes );
            model.addAttribute("message", filteredDishes.isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
            return "menu";


        } else if (searchCategory != null && searchPrice != null) {
            // return back filtered dishes from the service layer
            List<Dish> filteredDishes = dishService.getFilteredDishes(searchCategory, searchPrice);
            model.addAttribute ("dishes", filteredDishes.isEmpty()? dishService.getAllDishes() : filteredDishes );
            model.addAttribute("message", filteredDishes.isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
            return "menu";
        }*/


        // get paginated dishes from the service layer // pagination info
        Page<Dish> page = dishService.getPaginatedDishes(pageSize,pageNo,sortField,sortDirection );




        //model.addAttribute("dishes", dishService.getAllDishes());
        //model.addAttribute("dishes", dishService.getPaginatedDishes(pageSize,pageNo)); // page NUm comes from path variable
        model.addAttribute("dishes", page.getContent());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalItems", page.getTotalElements());







        // sorting info // to put in front end .html
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDirection", sortDirection);
        model.addAttribute("reverseSortDirection", sortDirection.equals("asc")? "desc" : "asc");


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
    //GET  /restaurant/update/{id} // Same Add a Dish Page
            // but with different name depending on conditions if fields have values or not
    @GetMapping("/update/{id}")
    public String updateDish (Model model, @PathVariable int id) {
        Optional<Dish> dishToUpdate;
        dishToUpdate = dishService.getDishById(id);

        // showing values in form prefilled from existing data in database
        model.addAttribute("dish", dishToUpdate.orElse(null));
        return "add-dish";
    }
    //update a dish
    @PostMapping("/update-dish")
    public String updateDish(@ModelAttribute Dish dish) {
        dishService.updateDish(dish);
        return "redirect:/restaurant/menu/1?message=Dish Has Been Updated Successfully!";
    }
}//class