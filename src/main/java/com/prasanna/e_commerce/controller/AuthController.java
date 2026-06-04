package com.prasanna.e_commerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.e_commerce.dto.LoginRequest;
import com.prasanna.e_commerce.dto.LoginResponse;
import com.prasanna.e_commerce.dto.RegisterDto;
import com.prasanna.e_commerce.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	
	@PostMapping("/register/user")
	public ResponseEntity<String> registerUser(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authService.register(registerDto,"ROLE_USER"));
	}
	
	@PostMapping("/register/admin")
	public ResponseEntity<String> registerAdmin(@RequestBody RegisterDto registerDto) {
		return ResponseEntity.ok(authService.register(registerDto,"ROLE_ADMIN"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
		return ResponseEntity.ok(authService.login(request));
	}
	
}
