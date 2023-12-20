package com.example.apimercadona;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import lombok.extern.java.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(servers = {@Server(url = "/", description = "Default Server URL")})
@SpringBootApplication
public class ApiMercadonaApplication {

	public static void main(String[] args) {

		SpringApplication.run(ApiMercadonaApplication.class, args);
		System.out.println();
	}


}
