package com.prasanna.e_commerce.dto;

import lombok.Data;

@Data
public class ProductDto {

	private String name;
	private String category;
	private String description;
	private Integer stockQuantity;
	private Double price;
	private Boolean isActive;
	
}
