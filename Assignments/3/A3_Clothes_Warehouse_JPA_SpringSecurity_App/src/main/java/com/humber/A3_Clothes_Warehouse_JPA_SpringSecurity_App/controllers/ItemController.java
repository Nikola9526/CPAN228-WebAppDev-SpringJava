package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.controllers;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Brand;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Item;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/warehouse") //for apis localhost:8080

public class ItemController {
    // constructor Injection
    @Autowired
    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    // constant name
    @Value("${store.name}")
    private String name;

    @Value("${page.size}")
    private int pageSize;

    //homepage
    //GET /warehouse/home
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("store", name);
        return "home";
    }

    // Inventory Page
    //GET /warehouse/inventory
   @GetMapping("/inventory/{pageNo}")
    public String getAllItems(Model model,
                              @PathVariable int pageNo,
                              @RequestParam (required = false) String message,
                              @RequestParam (required= false) String errormessage,
                              @RequestParam (required = false) String sortField,
                              @RequestParam (required = false) String sortDirection,
                              @RequestParam (required = false) Brand searchBrand,
                              @RequestParam (required = false) Double searchPrice) {

        // giving default values to sortField and sort direction
        sortField = sortField == null ? "id" : sortField;
        sortDirection = sortDirection == null ? "asc" : sortDirection;

        // setting variables, filterByBrand, filterByPrice so we now which one are we filtering, which method to call
        boolean filterByBrand = searchBrand != null;
        boolean filterByPrice = searchPrice != null;

       Page <Item> page;

       if (filterByBrand || filterByPrice) {
           // get only paginated items that is for only the entered brand or price or both
          page = itemService.getFilteredPaginatedItems(pageSize, pageNo, searchBrand, searchPrice);
           model.addAttribute("items", page.getContent());
           model.addAttribute("totalPages", page.getTotalPages());
           model.addAttribute("currentPage", pageNo);
           model.addAttribute("totalItems", page.getTotalElements());
           model.addAttribute("message",
                   page.getContent().isEmpty()? "Data was Not Filtered!" : "Data Filtered Successfully!");
           return "inventory";
       }
           // returns all paginated Items
           page = itemService.getPaginatedItems(pageSize, pageNo, sortField, sortDirection);

           model.addAttribute("items", page.getContent());
           model.addAttribute("totalPages", page.getTotalPages());
           model.addAttribute("currentPage", pageNo);
           model.addAttribute("totalItems", page.getTotalElements());

          // sorting info
          model.addAttribute("sortField", sortField);
          model.addAttribute("sortDirection", sortDirection);
          model.addAttribute("reverseSortDirection", sortDirection.equals("asc") ? "desc" : "asc");

          model.addAttribute("errormessage", errormessage);
          model.addAttribute("message", message);
        return "inventory";
   }

   //GET /warehouse/add-item
    // add a Item Form
    @GetMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        return "add-item";
    }

    // save a clothes item
    // POST /warehouse/add-item
    @PostMapping("/add-item")
    public String addItem(@ModelAttribute Item item, Model model) {
        // saving in DB JPA
        int statusCode = itemService.saveItem(item);
        // 1 is success , 0 for fail
        if (statusCode == 1) {
            return "redirect:/warehouse/inventory/1?message=Item added successfully!";
        } //else
        return "redirect:/warehouse/inventory/1?errormessage=item not added, Price Greater Than 1000 or Year is In the Future!";
    }
    // delete an item  /warehouse/delete/{id}
    //GET
    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable int id) {
        int deleteStatusCode = itemService.deleteItem(id);
        if (deleteStatusCode == 1) {
            return "redirect:/warehouse/inventory/1?message=Item deleted successfully!";
        } //else
        return "redirect:/warehouse/inventory/1?errormessage=Item not deleted, Does Not Exist";
    }
    // update item
    //GET /warehouse.update/{id} // Same as add item but diff name
    // Update Form
    @GetMapping("/update/{id}")
    public String updateItem(@PathVariable int id, Model model) {
        Optional<Item> itemToUpdate;
        itemToUpdate = itemService.getItemById(id);
        // showing values in from prefilled from existing data from DB
        model.addAttribute("item", itemToUpdate.orElse(null));
        return "add-item";
    }
    //update the Item
    @PostMapping("/update-item")
    public String updateItem(@ModelAttribute Item item ){
        //saving in DB
        int statusCode = itemService.updateItem(item);
        if (statusCode == 1) {
            return "redirect:/warehouse/inventory/1?message=Item updated successfully!";
        } //else 0
        return "redirect:/warehouse/inventory/1?errormessage=Item Did Not Updated!, Price Greater Than 1000 or Year is In the Future!";
    }
}