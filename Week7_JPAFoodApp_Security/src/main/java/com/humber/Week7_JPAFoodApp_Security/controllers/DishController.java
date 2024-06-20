package com.humber.Week7_JPAFoodApp_Security.controllers;

import com.humber.Week7_JPAFoodApp_Security.models.Dish;
import com.humber.Week7_JPAFoodApp_Security.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/restaurant") //for apis localhost:8080
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
//   only Menu and Home here

    //home-page
    //GET /restaurant/home
    @GetMapping("/home") //for methods
    public String home(Model model , @RequestParam(required = false) String message){
        model.addAttribute("restaurantName", name);
        model.addAttribute("message", message);
        return "home";
    }

    //menu page
    //GET /restaurant/menu
    @GetMapping("/menu/{pageNo}") // @PathVariable
    public String getAllDishes(Model model,
                               @RequestParam (required=false) String message,
                               @RequestParam (required = false) String errormessage,
                               @RequestParam (required = false) String searchCategory,
                               @RequestParam (required = false) Double searchPrice,
                               @PathVariable int pageNo,
                               @RequestParam (required = false) String sortField,
                               @RequestParam (required = false) String sortDirection){ // has to be false so first click on menu it does not sort

        // give default values to sort field and sort direction
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;


           if (searchCategory != null && searchPrice != null) {
               // get only paginated dishes that the category and price that was entered //& pagination info
               // showing all filtered dished on one page
               Page<Dish> filterPage = dishService.getPaginatedDishesFiltered(50,pageNo,searchCategory, searchPrice);

               model.addAttribute("dishes",filterPage.getContent() );
               model.addAttribute("totalPages",filterPage.getTotalPages());
               model.addAttribute("currentPage", pageNo);
               model.addAttribute("totalItems", filterPage.getTotalElements());
               model.addAttribute("message",
                       filterPage.getContent().isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
               model.addAttribute("errormessage", errormessage);
            return "menu";
        } //else
            //get All paginated dishes from the service layer //pagination info
            Page<Dish> page = dishService.getPaginatedDishes(pageSize,pageNo,sortField,sortDirection );
           // page NUm comes from path variable

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

}//class