package com.humber.Lab8_Week9_SpringRestAPI.repositories;

import com.humber.Lab8_Week9_SpringRestAPI.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //find category by Name
    Category findByName(String name);
}
