package com.humber.Week9SpringRestAPI.repositories;

import com.humber.Week9SpringRestAPI.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    //find category by Name
    Category findByName(String name);
}
