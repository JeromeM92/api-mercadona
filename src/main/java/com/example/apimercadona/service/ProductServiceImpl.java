package com.example.apimercadona.service;

import com.example.apimercadona.entity.Product;
import com.example.apimercadona.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductRepository productRepository;


    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId).orElse(null);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public void updateProductById(Product product, Long productId) {
        Product oldProduct = getProductById(productId);

        if(oldProduct != null){
            oldProduct.setProductName(product.getProductName());
            oldProduct.setDescription(product.getDescription());
            oldProduct.setPrice(product.getPrice());
            oldProduct.setCategory(product.getCategory());
            oldProduct.setDeal(product.getDeal());
            productRepository.save(oldProduct);
        }
    }

    @Override
    public void createProduct(Product product) {

        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {

        return productRepository.findAllProducts();
    }
}
