package com.suri.exception_handling.service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.suri.exception_handling.dto.UserRequest;
import com.suri.exception_handling.exception.UserNotFoundException;
import com.suri.exception_handling.model.User;
import com.suri.exception_handling.repo.UserRepo;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	
	public User saveUser(UserRequest userRequest) {
		User user = new User(0, userRequest.name(), 
				userRequest.email(), userRequest.city(), userRequest.gender());
		return userRepo.save(user);
	}

	public List<User> getAllUsers(){
		
		if(userRepo.findAll().isEmpty()) {
			throw new UserNotFoundException("No users found in the database");
		}
		else
			System.out.println("getting data from db: "+userRepo.findAll());
		return userRepo.findAll();
				
	}
	
	public User getUser(long id) {
	   return userRepo.findById(id).orElseThrow(
			   ()->new UserNotFoundException("User not found with id: " + id));
	}

	public List<User> saveAll(List<UserRequest> userRequest) {
	    List<User> users = userRequest.stream()
	            .map(req -> new User(0L,req.name(), req.email(), req.city(), req.gender()))
	            .collect(Collectors.toList());
	    return userRepo.saveAll(users);
	}
	
	
}
