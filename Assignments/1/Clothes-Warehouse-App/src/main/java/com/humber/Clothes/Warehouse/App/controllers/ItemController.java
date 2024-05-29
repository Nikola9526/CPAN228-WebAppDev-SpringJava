package com.humber.Clothes.Warehouse.App.controllers;
import com.humber.Clothes.Warehouse.App.models.Brand;
import com.humber.Clothes.Warehouse.App.models.Item;
import com.humber.Clothes.Warehouse.App.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse") // for apis localhost:8080

public class ItemController {

    @Autowired
    private ItemService itemService;

    //constant name
    @Value("${store.name}")
    private String name;

    @Value("${inventory.heading}")
    private String heading;

    // home-page
    // GET endpoint /warehouse/home
    // returns home page, store name
    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("store", name); // 'store' is used in html //'name' is here in Controller
        return "home";// home.html view name
    }

    //GET endpoint /restaurants/inventory
    //inventory page returns all existing items in inventory
    @GetMapping("/inventory")
    public String getAllItems(Model model){
        model.addAttribute("subTitle", heading);
        model.addAttribute("items", itemService.getAllItems());
        return "inventory"; //inventory.html view
    }
    //GET endpoint /warehouse/add-item
    @GetMapping("/add-item")
    public String addItem(Model model){
        model.addAttribute("item", new Item()); // creates new object of entered inventory item // shows newly item on screen
        model.addAttribute("brands", Brand.values()); // In form shows the enum Brands all the different Brands to choose from
        return "add-item";//  add-item.html view
    }

    // POST endpoint /warehouse/add-item
    @PostMapping("add-item")
    public String addItem(@ModelAttribute Item item, Model model){
        //performs validations
        // New item will not be added if Price is more than 1000 or Year is More than current year 2024
        // it stays on the screen
        if(item.getPrice() > 1000 ){
            model.addAttribute("error", "Item price exceeds 1000");
            return "add-item";
        } else if (item.getYear_creation() > 2024) {
            model.addAttribute("error1","Year can not be in Future 2024 or less");
            return "add-item";
        }
        // if price is 1000 or less or year is current it will go to the inventory screen and display the newly added item
        model.addAttribute("items",item);
        return "inventory"; // inventory.html view
    }
}// class