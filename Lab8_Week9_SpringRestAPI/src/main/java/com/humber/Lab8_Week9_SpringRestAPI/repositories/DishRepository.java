package com.humber.Lab8_Week9_SpringRestAPI.repositories;

import com.humber.Lab8_Week9_SpringRestAPI.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// Jpa already a bean no need for @Repo
public interface DishRepository extends JpaRepository<Dish, Long> { // data type of PK
    // create custom Jpa query
    // find dish by name
    //@Query("SELECT d FROM Dish WHERE d.name = :name") // or ?!
    Dish findDishByName(String name);
}
