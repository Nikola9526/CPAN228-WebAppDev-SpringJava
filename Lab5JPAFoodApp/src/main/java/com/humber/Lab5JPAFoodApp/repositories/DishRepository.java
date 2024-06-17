package com.humber.Lab5JPAFoodApp.repositories;

import com.humber.Lab5JPAFoodApp.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  DishRepository extends JpaRepository<Dish, Integer> {
    //anything extending from jpa is already a component so no need for @Repository

    // new
    Page <Dish> findByCategoryAndPrice(String searchCategory, Double searchPrice, Pageable pageable);

}
