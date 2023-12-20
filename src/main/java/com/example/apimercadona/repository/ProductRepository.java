package com.example.apimercadona.repository;

import com.example.apimercadona.entity.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

    @Query("SELECT a FROM Product a")
    List<Product> findAllProducts();
}
