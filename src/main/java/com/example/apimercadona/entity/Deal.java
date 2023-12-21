package com.example.apimercadona.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Deal {

    @Id
    //Equivaut à autoIncremente
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dealId;
    private String dealName;

    private LocalDate startDate;
    private LocalDate endDate;
    private Float discountPercentage;
    public Deal(String dealName, Float discountPercentage, LocalDate startDate, LocalDate endDate) {
        this.dealName = dealName;
        this.discountPercentage = discountPercentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
