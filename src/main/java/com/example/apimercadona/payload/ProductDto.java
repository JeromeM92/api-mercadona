package com.example.apimercadona.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private String productName;
    private Float price;
    private String description;
    private Long categoryId;
    private String categoryName;
    private Long dealId;
    private String imageBase64;
}

