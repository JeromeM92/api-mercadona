package com.example.apimercadona.ws;

import com.example.apimercadona.entity.Category;
import com.example.apimercadona.entity.Deal;
import com.example.apimercadona.entity.Product;
import com.example.apimercadona.payload.ProductDto;
import com.example.apimercadona.repository.CategoryRepository;
import com.example.apimercadona.repository.DealRepository;
import com.example.apimercadona.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.List;

@RequestMapping(ApiRegistration.API_REST + ApiRegistration.PRODUCT)
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductWs {
   @Autowired
   private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DealRepository dealRepository;
    private Category getCategory(Long categoryId) {
        if (categoryId != null) {
            return categoryRepository.findById(categoryId).orElse(null);
        }
        return null;
    }

    private Deal getDeal(Long dealId) {
        if (dealId != null) {
            return dealRepository.findById(dealId).orElse(null);
        }
        return null;
    }
    private String storeImage(MultipartFile file) {
        if (file.isEmpty()) {
            return null;
        }

        try {
            String tempDir = System.getProperty("java.io.tmpdir");
            Path destinationFile = Paths.get(tempDir, file.getOriginalFilename());
            Files.copy(file.getInputStream(), destinationFile);

            return destinationFile.toString();
        } catch (Exception e) {
            // Gérer l'exception
            return null;
        }
    }

    @GetMapping("/all-products")
    public List<Product> getAllProducts() {

        return productService.getAllProducts();
    }

    @PostMapping("/create-product")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto) {
        try {
            byte[] imageBytes = Base64.getDecoder().decode(productDto.getImageBase64());

            Product product = new Product();
            // Settez les autres champs du produit
            product.setImage(imageBytes); // Stockez les données binaires de l'image
            product.setProductName(productDto.getProductName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            Category category = getCategory(productDto.getCategoryId());
            Deal deal = getDeal(productDto.getDealId());

            product.setCategory(category);
            product.setDeal(deal);
            productService.createProduct(product);

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Gérer l'exception
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("{productId}")
    public Product getProductById(@PathVariable("productId") Long productId) {
        return null;
    }

    /*@PostMapping("/create-product")
    public void createProduct(@RequestParam("productName") String productName,
                              @RequestParam("price") Float price,
                              @RequestParam("description") String description,
                              @RequestParam("category") Long categoryId,
                              @RequestParam(value = "deal", required = false) Long dealId,
                              @RequestParam("image") MultipartFile imageFile) {
        String imageUrl = storeImage(imageFile);
        Product product = new Product();
        product.setProductName(productName);
        product.setPrice(price);
        product.setDescription(description);
        Category category = getCategory(categoryId);
        Deal deal = getDeal(dealId);
        product.setCategory(category);
        product.setDeal(deal);
        product.setImageUrl(imageUrl);

        productService.createProduct(product);
    }*/

    @PutMapping("/update")
    public void updateProductById(@RequestBody Product updatedProduct, @PathVariable Long productId) {
        Product existingProduct = productService.getProductById(productId);

        existingProduct.setProductName(updatedProduct.getProductName());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setImage(updatedProduct.getImage());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setDeal(updatedProduct.getDeal());

        productService.updateProductById(existingProduct, productId);

    }
    @DeleteMapping("/delete/{productId}")
    public void deleteProductById(@PathVariable Long productId) {
        productService.deleteProductById(productId);
    }
}
