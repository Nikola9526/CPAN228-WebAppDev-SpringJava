package com.humber.Week12AUnitTesting.repositories;

import com.humber.Week12AUnitTesting.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

// Jpa already a bean no need for @Repo
public interface DishRepository extends JpaRepository<Dish, Long> { // data type of PK
    // create custom Jpa query
    // find dish by name

    //@Query("SELECT d FROM Dish WHERE d.name = :name") // or ?!
    Dish findDishByName(String name);
}
