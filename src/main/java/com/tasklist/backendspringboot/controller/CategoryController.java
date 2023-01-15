package com.tasklist.backendspringboot.controller;

import com.tasklist.backendspringboot.entity.Category;
import com.tasklist.backendspringboot.repository.CategoryRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.PublicKey;

@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/add")
    public Category add(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

}
