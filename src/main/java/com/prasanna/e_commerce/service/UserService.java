package com.prasanna.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.repository.UserRepo;

@Service
public class UserService {

	@Autowired
	private UserRepo repo;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	public String register(User user) {
		if (repo.existsByUsername(user.getUsername()) || repo.existsByEmail(user.getEmail()))
			return "User already exists";
		user.setPassword(encoder.encode(user.getPassword()));
		if(user.getRole() == null)
			user.setRole("USER");
		repo.save(user);
		return "Registered successfully";
	}
	
	public String login(String username, String password) {
		User user=repo.findByUsername(username);
		if (user == null || !encoder.matches(password, user.getPassword())) {
			return "Login failed";
		}
		return "Login success as : "+user.getRole();
	}
	
	public User getByUsername(String username) {
		
		if (username==null)
			throw new RuntimeException("User not found with username: " + username);
		
		return repo.findByUsername(username);
	}
	
}
