package com.example.apimercadona.payload;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DealDto {
    private Long dealId;
    private String dealName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Float discountPercentage;

}

