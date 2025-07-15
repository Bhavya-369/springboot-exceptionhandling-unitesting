package com.suri.exception_handling.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.suri.exception_handling.model.User;

public interface UserRepo extends JpaRepository<User, Long>{

	Optional<User> findById(long id);

}
