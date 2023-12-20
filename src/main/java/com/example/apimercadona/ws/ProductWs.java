package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Product;
import com.example.apimercadona.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.PRODUCT)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductWs {

    @Autowired
    private ProductService productService;

    @GetMapping("/all-products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @GetMapping("{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {
        return null;
    }

    @PostMapping("/create-product")
    public void createProduct(@RequestBody Product product) {
        productService.createProduct(product);
    }

    @PutMapping("/update")
    public void updateProductById(@RequestBody Product updatedProduct, @PathVariable Long productId) {
        Product existingProduct = productService.getProductById(productId);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImageUrl(updatedProduct.getImageUrl());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setDeal(updatedProduct.getDeal());

        productService.updateProductById(existingProduct, productId);

    }
    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
    }
}
