package com.prasanna.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.exception.ResourceNotFoundException;
import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.repository.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findByIsActiveTrue();
	}

	public Product getProductById(Long id) {
		Product product=productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id : "+id));
		
		if(!product.getIsActive())
			throw new ResourceNotFoundException("Product not found with id : "+id); 
		
		return product;
	}
	
	public List<Product> searchProduct(String keyword){
		return productRepo.searchProduct(keyword);
	}
	
}
