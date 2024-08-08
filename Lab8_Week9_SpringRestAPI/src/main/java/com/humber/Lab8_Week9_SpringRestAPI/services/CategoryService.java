package com.humber.Lab8_Week9_SpringRestAPI.services;

import com.humber.Lab8_Week9_SpringRestAPI.models.Category;
import com.humber.Lab8_Week9_SpringRestAPI.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    // constructor Injection
    // injecting categoryRepo into Category Service
    private final CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    // get category by name
    public Category getCategoryByName(String name){
        return categoryRepository.findByName(name);
    }


}
