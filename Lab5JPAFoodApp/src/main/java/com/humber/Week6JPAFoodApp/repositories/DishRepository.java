package com.humber.Week6JPAFoodApp.repositories;

import com.humber.Week6JPAFoodApp.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface  DishRepository extends JpaRepository<Dish, Integer> {

    List<Dish> findByCategoryIgnoreCaseAndPrice(String searchCategory, Double searchPrice);
    List<Dish> findByCategoryAndPrice(String searchCategory, Double searchPrice);

    List<Dish> findByCategory(String searchCategory);



    List <Dish> findByPrice(Double searchPrice);

    List<Dish> findByPriceBetween(Double lower, Double upper);
    // anything extending from jpa is already a component so no need for @Repository


    // new
    Page <Dish> findByCategory(String category, Pageable pageable);


}
