package com.example.apimercadona;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import com.example.apimercadona.entity.Category;
import com.example.apimercadona.repository.CategoryRepository;
import com.example.apimercadona.service.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    private Category category;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        category = new Category();
        category.setCategoryName("Electronics");
    }

    @Test
    public void testCreateCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        Category created = categoryService.createCategory(category);

        assertEquals(category.getCategoryName(), created.getCategoryName());
        verify(categoryRepository).save(category);
    }
}
