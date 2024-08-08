package com.humber.Week9SpringRestAPI.services;

import com.humber.Week9SpringRestAPI.models.Category;
import com.humber.Week9SpringRestAPI.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    // constructor Injection
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // get category by name
    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }


}
