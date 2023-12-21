package com.example.apimercadona.service;

import com.example.apimercadona.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long categoryId);
    Category createCategory(Category category);
    Category updateCategory(Category category, Long categoryId);
    void deleteCategoryById(Long categoryId);
}
