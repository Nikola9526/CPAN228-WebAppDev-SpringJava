package com.humber.Lab4JPA_dishApp.repositories;

import com.humber.Lab4JPA_dishApp.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByCategoryAndPrice(String searchCategory, Double searchPrice);

    List<Dish> findByCategory(String searchCategory);

    List <Dish> findByPrice(Double searchPrice);

    List<Dish> findByPriceBetween(Double lower, Double upper);
    // anything extending from jpa is already a component so no need for @Repository


}
