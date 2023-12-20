package com.example.apimercadona.service;

import com.example.apimercadona.entity.Category;

import java.util.List;

public interface CategoryService {
    Category getCategoryById(Long categoryId);

    void deleteCategoryById(Long categoryId);

    void updateCategoryById(Category category, Long categoryId);

    void createCategory(Category category);

    List<Category> getAllCategories();
}
