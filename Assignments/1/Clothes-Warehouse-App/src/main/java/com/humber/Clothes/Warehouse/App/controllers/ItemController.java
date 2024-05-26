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

    //Add Method to return all items from static data repo

    // home-page
    // GET endpoint /restaurants/
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("store", name); // 'store' is used in html 'name' is here
        return "home";// html view name 'home.html'
    }

    //GET endpoint /restaurants/inventory
    @GetMapping("/inventory")
    public String getAllItems(Model model){
        model.addAttribute("subTitle", heading);
        model.addAttribute("items", itemService.getAllItems());
        return "inventory";
    }

    @GetMapping("/add-item")
    public String addItem(Model model){
        model.addAttribute("item", new Item());
        model.addAttribute("brands", Brand.values());
        return "add-item";// view html page
    }

    // POST
    @PostMapping("add-item")
    public String addItem(@ModelAttribute Item item, Model model){
        model.addAttribute("items",item);
        return "inventory";
    }
}// class