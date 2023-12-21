package com.example.apimercadona.service;

import com.example.apimercadona.entity.Category;
import com.example.apimercadona.entity.Product;
import com.example.apimercadona.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository; // Assurez-vous d'avoir un CategoryRepository

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepository.findAll().forEach(categories::add);
        return categories;
    }

    @Override
    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        if (categoryRepository.existsById(categoryId)) {
            category.setCategoryId(categoryId); // Assurez-vous de définir l'ID de la catégorie
            return categoryRepository.save(category);
        }
        return null; // ou gérez cette situation autrement, par exemple, en lançant une exception
    }

    @Override
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);
    }
}
