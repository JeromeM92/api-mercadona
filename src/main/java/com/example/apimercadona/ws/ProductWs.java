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
import java.util.stream.Collectors;

@RestController
@RequestMapping(ApiRegistration.API_REST + ApiRegistration.PRODUCT)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductWs {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private DealRepository dealRepository;

    // Méthode auxiliaire pour obtenir une catégorie par son ID
    private Category getCategory(Long categoryId) {
        return categoryId != null ? categoryRepository.findById(categoryId).orElse(null) : null;
    }

    // Méthode auxiliaire pour obtenir un deal par son ID
    private Deal getDeal(Long dealId) {
        return dealId != null ? dealRepository.findById(dealId).orElse(null) : null;
    }

    // Convertir un Product en ProductDto
    private ProductDto convertToDto(Product product) {
        ProductDto dto = new ProductDto();
        dto.setProductName(product.getProductName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        if (product.getCategory() != null) {
            dto.setCategoryName(product.getCategory().getCategoryName());
        }
        // Gestion de l'image et autres champs si nécessaire
        return dto;
    }

    // Convertir un ProductDto en Product
    private Product convertToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductName(productDto.getProductName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setCategory(getCategory(productDto.getCategoryId()));
        product.setDeal(getDeal(productDto.getDealId()));
        // Gestion de l'image et autres champs si nécessaire
        return product;
    }

    // Récupérer tous les produits
    @GetMapping("/all-products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDto> productDtos = products.stream().map(this::convertToDto).collect(Collectors.toList());
        return ResponseEntity.ok(productDtos);
    }

    // Créer un produit
    @PostMapping("/create-product")
    public ResponseEntity<ProductDto> createProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") Float price,
            @RequestParam("description") String description,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("dealId") Long dealId,
            @RequestParam(value = "image", required = false) MultipartFile imageFile) {
        try {
            ProductDto productDto = new ProductDto();
            productDto.setProductName(productName);
            productDto.setPrice(price);
            productDto.setDescription(description);
            productDto.setCategoryId(categoryId);
            productDto.setDealId(dealId);

            Product product = convertToEntity(productDto);
            Product savedProduct = productService.createProduct(product);
            return ResponseEntity.ok(convertToDto(savedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Récupérer un produit par son ID
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long productId) {
        Product product = productService.getProductById(productId);
        if (product != null) {
            return ResponseEntity.ok(convertToDto(product));
        }
        return ResponseEntity.notFound().build();
    }

    // Mettre à jour un produit
    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductDto> updateProductById(@RequestBody ProductDto productDto, @PathVariable Long productId) {
        try {
            Product existingProduct = productService.getProductById(productId);
            if (existingProduct == null) {
                return ResponseEntity.notFound().build();
            }
            Product updatedProduct = convertToEntity(productDto);
            updatedProduct.setProductId(productId); // Assurez-vous de conserver l'ID original
            productService.updateProductById(updatedProduct, productId);
            return ResponseEntity.ok(convertToDto(updatedProduct));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Supprimer un produit
    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProductById(@PathVariable Long productId) {
        try {
            productService.deleteProductById(productId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}

