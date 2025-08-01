package com.prasanna.e_commerce.dto;

import lombok.Data;

@Data
public class RegisterRequest {
	
	public String firstname;
	public String lastname;
	public String username;
	public String email;
	public String address;
	public String phone;
	public String password;
	public String role;
	
	
}
