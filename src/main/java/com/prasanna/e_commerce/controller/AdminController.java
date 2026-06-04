package com.prasanna.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.e_commerce.dto.ProductDto;
import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.service.AdminService;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProducts() {
	    List<Product> products = adminService.getAllProducts();
	    if (products.isEmpty())
	        return ResponseEntity.noContent().build();
	    return ResponseEntity.ok(products);
	}

	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
	    return ResponseEntity.ok(adminService.getProductById(id));
	}

	@GetMapping("/products/search")
	public ResponseEntity<List<Product>> searchProduct(@RequestParam String keyword) {
	    List<Product> products = adminService.searchProduct(keyword);
	    if (products.isEmpty())
	        return ResponseEntity.noContent().build();
	    return ResponseEntity.ok(products);
	}
	
	@PostMapping("/add/product")
	public ResponseEntity<Product> addProduct(@RequestBody ProductDto productDto){
		return ResponseEntity.ok(adminService.addProduct(productDto));
	}
	
	@PutMapping("/update/product/{id}")
	public ResponseEntity<Product> updateProductById(@PathVariable Long id,@RequestBody ProductDto productDto){
		return ResponseEntity.ok(adminService.updateProductById(id,productDto));
	}
	
	@DeleteMapping("/delete/product/{id}")
	public ResponseEntity<Product> deleteProductById(@PathVariable Long id){
		return ResponseEntity.ok(adminService.deleteProductById(id));
	}
	
}
