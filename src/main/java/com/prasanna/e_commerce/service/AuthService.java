package com.prasanna.e_commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.dto.LoginRequest;
import com.prasanna.e_commerce.dto.LoginResponse;
import com.prasanna.e_commerce.dto.RegisterDto;
import com.prasanna.e_commerce.exception.AuthenticationException;
import com.prasanna.e_commerce.exception.ConflictException;
import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.repository.UserRepo;

@Service
public class AuthService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtService jwtService;
	
	public String register(RegisterDto registerDto,String role) {
		
		if(userRepo.existsByUsername(registerDto.getUsername()))
			throw new ConflictException("Username already exist!");
		
		if(userRepo.existsByEmail(registerDto.getEmail()))
			throw new ConflictException("Email already exist!");
			
		User user=new User();
		
		user.setFirstName(registerDto.getFirstName());
		user.setLastName(registerDto.getLastName());
		user.setUsername(registerDto.getUsername());
		user.setEmail(registerDto.getEmail());
		user.setAddress(registerDto.getAddress());
		user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
		user.setRole(role);
		
		userRepo.save(user);
		
		return "Registered successfully";
	}
	
	public LoginResponse login(LoginRequest request) {
		
		Authentication authentication=authenticationManager.
				authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		if(!authentication.isAuthenticated()) 
			throw new AuthenticationException("Invalid credentials!");
		
		LoginResponse loginResponse=new LoginResponse();
		loginResponse.setToken(jwtService.generateToken(request.getUsername()));
		
		return loginResponse;
	}
	
}
