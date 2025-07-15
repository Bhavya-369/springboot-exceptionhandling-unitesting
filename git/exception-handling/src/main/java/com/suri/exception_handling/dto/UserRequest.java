package com.suri.exception_handling.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRequest

(
		@NotNull(message="Name should not be empty")
		String name, 
		@Email(message = "Invalid email format")
		@NotBlank(message="must provide email")
		String email,
		String city,
		String gender
		
) 

{

}
