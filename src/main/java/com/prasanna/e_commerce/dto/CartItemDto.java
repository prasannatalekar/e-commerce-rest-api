package com.prasanna.e_commerce.dto;

import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.model.User;

import lombok.Data;

@Data
public class CartItemDto {
	
	private User user;
	private Product product;
	private Integer quantity;
	private Double totalAmount;
}
