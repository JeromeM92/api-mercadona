package com.example.apimercadona.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {


    @Id
    //Equivaut à autoIncremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    private String categoryName;

    @OneToMany(mappedBy = "category")
    private List<Product> products;


    // Constructor
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category () {

    }
}
