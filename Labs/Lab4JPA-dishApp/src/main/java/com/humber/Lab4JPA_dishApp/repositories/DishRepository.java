package com.humber.Lab4JPA_dishApp.repositories;

import com.humber.Lab4JPA_dishApp.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  DishRepository extends JpaRepository<Dish, Integer> {


}
