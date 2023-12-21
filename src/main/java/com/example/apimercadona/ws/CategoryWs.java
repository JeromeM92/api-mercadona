package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Category;
import com.example.apimercadona.payload.CategoryDto;
import com.example.apimercadona.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.CATEGORY)
@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class CategoryWs {

    @Autowired
    private CategoryService categoryService;
    // Convertir Category en CategoryDto
    private CategoryDto convertToDto(Category category) {
        return new CategoryDto(category.getCategoryId(), category.getCategoryName());
    }
    // Convertir CategoryDto en Category
    private Category convertToEntity(CategoryDto categoryDto) {
        return new Category(categoryDto.getCategoryName()); // Assurez-vous que le constructeur de Category est approprié
    }
    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        List<CategoryDto> categories = categoryService.getAllCategories()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.getCategoryById(id);
        if (category != null) {
            return ResponseEntity.ok(convertToDto(category));
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping("/create-category")
    public ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryService.createCategory(convertToEntity(categoryDto));
        return ResponseEntity.ok(convertToDto(category));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@PathVariable Long id, @RequestBody CategoryDto categoryDto) {
        Category updatedCategory = categoryService.updateCategory(convertToEntity(categoryDto), id);
        if (updatedCategory != null) {
            return ResponseEntity.ok(convertToDto(updatedCategory));
        }
        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategoryById(id);
    }
}
