package com.suri.exception_handling;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info=@Info(title="User Service API",version = "1.0",description = "contains user details"))
public class ExceptionHandlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExceptionHandlingApplication.class, args);
	}

}
