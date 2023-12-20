package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Category;
import com.example.apimercadona.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.CATEGORY)
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategoryWs {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all-categories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("{categoryId}")
    public Category getCategoryById(@PathVariable("categoryId") Long categoryId) {
        return categoryService.getCategoryById(categoryId);
    }

    @PostMapping("/create-category")
    public void createCategory(@RequestBody Category category) {
        categoryService.createCategory(category);
    }

    @PutMapping("/update")
    public void updateCategoryById(@RequestBody Category category, @PathVariable Long categoryId) {
        Category existingCategory = categoryService.getCategoryById(categoryId);
        existingCategory.setCategoryName(category.getCategoryName());
        categoryService.updateCategoryById(category, categoryId);
    }
    @DeleteMapping("/delete/{categoryId}")
    public void deleteCategoryById(@PathVariable Long categoryId) {
        categoryService.deleteCategoryById(categoryId);
    }
}
