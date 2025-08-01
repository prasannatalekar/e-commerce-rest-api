package com.prasanna.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.e_commerce.dto.LoginRequest;
import com.prasanna.e_commerce.dto.RegisterRequest;
import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public String register(@RequestBody RegisterRequest request) {
		User user=new User(null, request.getFirstname(), request.getLastname(), request.getUsername(), 
				request.getEmail(), request.getPassword(), request.getAddress(), request.getPhone(), request.getRole());
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody LoginRequest request) {
		return userService.login(request.getUsername(), request.getPassword());
	}
	
}
