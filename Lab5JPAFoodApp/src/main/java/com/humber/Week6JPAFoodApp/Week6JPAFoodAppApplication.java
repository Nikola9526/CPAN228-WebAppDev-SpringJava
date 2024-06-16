package com.humber.Week6JPAFoodApp;

import com.humber.Week6JPAFoodApp.models.Dish;
import com.humber.Week6JPAFoodApp.services.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Week6JPAFoodAppApplication implements CommandLineRunner {

	@Autowired
	// constructor injection // making object of dish service class
	private final DishService dishService;

	public Week6JPAFoodAppApplication(DishService dishService) {
		this.dishService = dishService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Week6JPAFoodAppApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		// anything here will excuite when app is run

		System.out.println("Hello from CLR!");
		dishService.saveDish(new Dish(1, "Pizza", "Non-Veg", 12.0));
		dishService.saveDish(new Dish(2, "Burger", "Non-Veg", 8.5));
		dishService.saveDish(new Dish(3, "Pasta", "Veg", 10.0));
		dishService.saveDish(new Dish(4, "Salad", "Veg", 7.0));
		dishService.saveDish(new Dish(5, "Chicken Wings", "Non-Veg", 15.0));
		dishService.saveDish(new Dish(6, "Fish and Chips", "Non-Veg", 14.0));
		dishService.saveDish(new Dish(7, "Tacos", "Veg", 9.0));
		dishService.saveDish(new Dish(8, "Steak", "Non-Veg", 20.0));
		dishService.saveDish(new Dish(9, "Sushi", "Non-Veg", 18.0));
		dishService.saveDish(new Dish(10, "Paneer Butter Masala", "Veg", 12.0));
		dishService.saveDish(new Dish(11, "Mushroom Risotto", "Veg", 13.0));
		dishService.saveDish(new Dish(12, "Lamb Chops", "Non-Veg", 20.0));

		// id value dose not matter here, JPA will auto make id

		System.out.println("All Dishes Saved!");



	}
}
