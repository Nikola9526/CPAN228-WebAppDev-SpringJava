package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.controllers;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Brand;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Item;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services.ItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/warehouse/admin")

public class AdminController {
    //constructor Injection
    private final ItemService itemService;
    public AdminController(ItemService itemService) {this.itemService = itemService;}


    //GET /warehouse/add-item
    // add a Item Form
    @GetMapping("/add-item")
    public String addItem(Model model) {
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        return "admin/add-item";
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
        return "admin/add-item";
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

}//class