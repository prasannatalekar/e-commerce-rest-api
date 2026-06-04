package com.prasanna.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.dto.ProductDto;
import com.prasanna.e_commerce.exception.ResourceNotFoundException;
import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.repository.ProductRepo;

@Service
public class AdminService {

	@Autowired
	private ProductRepo productRepo;
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Long id) {
		Product product=productRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found with id : "+id));
				
		return product;
	}
	
	public List<Product> searchProduct(String keyword){
		return productRepo.searchAllProducts(keyword);
	}
	
	public Product addProduct(ProductDto productDto) {
		
		Product product=new Product();
		
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setDescription(productDto.getDescription());
		product.setStockQuantity(productDto.getStockQuantity());
		product.setPrice(productDto.getPrice());
		product.setIsActive(productDto.getIsActive());
		
		return productRepo.save(product);
	}
	
	public Product updateProductById(Long productId,ProductDto productDto) {
		
		Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
		
		product.setName(productDto.getName());
		product.setCategory(productDto.getCategory());
		product.setDescription(productDto.getDescription());
		product.setStockQuantity(productDto.getStockQuantity());
		product.setPrice(productDto.getPrice());
		product.setIsActive(productDto.getIsActive());
		
		return productRepo.save(product);
	}
	
	public Product deleteProductById(Long productId) {
		
		Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found"));
		product.setIsActive(false);
		productRepo.save(product);
		
		return product;
	}
	
}
