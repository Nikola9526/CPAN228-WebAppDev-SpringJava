package com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App;

import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Brand;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.models.Item;
import com.humber.A3_Clothes_Warehouse_JPA_SpringSecurity_App.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class A3_Clothes_Warehouse_JPA_SpringSecurity_AppApplication implements CommandLineRunner {
	@Autowired
	// constructor Injection
	private final ItemService itemService;

	public A3_Clothes_Warehouse_JPA_SpringSecurity_AppApplication(ItemService itemService) {
		this.itemService = itemService;
	}

	public static void main(String[] args) {
		SpringApplication.run(A3_Clothes_Warehouse_JPA_SpringSecurity_AppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello from CLR! Adding Items");
		itemService.saveItem(new Item(1,"Summer Dress", Brand.StoneIsland, 1990,80));
		itemService.saveItem(new Item(2, "Blue Summer Dress", Brand.StoneIsland, 1990, 80));
		itemService.saveItem(new Item(3, "Winter Coat", Brand.Nike, 2000, 150));
		itemService.saveItem(new Item(4, "Running Shoes", Brand.Adidas, 2015, 120));
		itemService.saveItem(new Item(5, "Leather Jacket", Brand.Gucci, 1995, 300));
		itemService.saveItem(new Item(6, "Jeans", Brand.Levi, 2010, 100));
		itemService.saveItem(new Item(7, "Sneakers", Brand.Puma, 2020, 90));
		itemService.saveItem(new Item(8, "T-Shirt", Brand.Zara, 2021, 40));
		itemService.saveItem(new Item(9, "Sweater", Brand.UnitedColorsOfBenetton, 2018, 70));
		itemService.saveItem(new Item(10, "Skirt", Brand.LouisVuitton, 2017, 200));
		itemService.saveItem(new Item(11, "Blazer", Brand.LouisVuitton, 2016, 250));
		itemService.saveItem(new Item(12, "Polo Shirt", Brand.Guess, 2019, 60));
		itemService.saveItem(new Item(13, "Hoodie", Brand.Balenciaga, 2022, 180));

		System.out.println("All Items Saved!");
	}
}
