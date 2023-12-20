package com.example.apimercadona.payload;

import lombok.Data;

@Data
public class LoginDto {
    private String userEmail;
    private String password;
}
