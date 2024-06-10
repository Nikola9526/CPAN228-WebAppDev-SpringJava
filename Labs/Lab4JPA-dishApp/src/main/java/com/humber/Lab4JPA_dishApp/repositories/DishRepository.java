package com.humber.Lab4JPA_dishApp.repositories;

import com.humber.Lab4JPA_dishApp.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByCategoryAndPrice(String searchCategory, Double searchPrice);


}
