package com.prasanna.e_commerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasanna.e_commerce.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	User findByUsername(String username);
	boolean existsByUsername(String username);
	boolean existsByEmail(String email);
}
