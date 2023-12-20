package com.example.apimercadona.repository;

import com.example.apimercadona.entity.Category;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {

    @Query("SELECT a FROM Category a")
    List<Category> findAllCategories();
}
