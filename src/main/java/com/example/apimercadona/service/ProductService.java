package com.example.apimercadona.service;

import com.example.apimercadona.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product getProductById(Long productId);
    Product updateProductById(Product product, Long productId);
    void deleteProductById(Long productId);
}

