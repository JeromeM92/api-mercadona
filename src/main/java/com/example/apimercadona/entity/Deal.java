package com.example.apimercadona.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Deal {

    @Id
    //Equivaut à autoIncremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;
    private String dealName;

    private LocalDate startDate;
    private LocalDate endDate;
    private Double discountPercentage;

    public Deal(String dealName, LocalDate startDate, LocalDate endDate, Double discountPercentage) {
        this.dealName = dealName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.discountPercentage = discountPercentage;
    }

    public Deal () {

    }
}
