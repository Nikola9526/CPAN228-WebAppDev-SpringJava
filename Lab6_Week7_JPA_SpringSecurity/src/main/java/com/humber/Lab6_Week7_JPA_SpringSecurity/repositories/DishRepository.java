package com.humber.Lab6_Week7_JPA_SpringSecurity.repositories;

import com.humber.Lab6_Week7_JPA_SpringSecurity.models.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  DishRepository extends JpaRepository<Dish, Integer> {
    //anything extending from jpa is already a component so no need for @Repository

    // new
    Page <Dish> findByCategoryAndPrice(String searchCategory, Double searchPrice, Pageable pageable);

}
