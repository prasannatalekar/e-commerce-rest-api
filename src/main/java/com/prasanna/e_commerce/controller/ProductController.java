package com.prasanna.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("")
	public ResponseEntity<List<Product>> getAllProducts(){		
		
		List<Product> products = productService.getAllProducts();
		
	    if (products.isEmpty())
	        return ResponseEntity.noContent().build();
	    
	    return ResponseEntity.ok(products);
	    
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		return ResponseEntity.ok(productService.getProductById(id));
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword){
		
		List<Product> products = productService.searchProduct(keyword);
		
	    if (products.isEmpty())
	        return ResponseEntity.noContent().build();
	    
	    return ResponseEntity.ok(products);
	}
	
}
