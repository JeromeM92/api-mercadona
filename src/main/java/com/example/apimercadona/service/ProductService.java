package com.example.apimercadona.service;

import com.example.apimercadona.entity.Product;

import java.util.List;

public interface ProductService {

    Product getProductById(Long productId);

    void deleteProductById(Long productId);

    void updateProductById(Product product, Long productId);

    void createProduct(Product product);

    List<Product> getAllProducts();
}
