package com.suri.exception_handling.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.suri.exception_handling.dto.UserRequest;
import com.suri.exception_handling.model.User;
import com.suri.exception_handling.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/save")
	@ResponseStatus(HttpStatus.CREATED)
	public  ResponseEntity<?> saveUser(@RequestBody @Valid List<UserRequest> users){
		userService.saveAll(users);		
		return ResponseEntity.ok("User saved");
	}
	
	@GetMapping("/getAll")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<User>> getAllUsers(){		
		return ResponseEntity.ofNullable(userService.getAllUsers());
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public User getUserById(@PathVariable long id) {
		return userService.getUser(id);
	}

}
