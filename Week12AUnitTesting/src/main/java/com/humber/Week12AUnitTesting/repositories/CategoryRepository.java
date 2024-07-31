package com.humber.Week12AUnitTesting.repositories;

import com.humber.Week12AUnitTesting.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //find category by Name
    Category findByName(String name);
}
