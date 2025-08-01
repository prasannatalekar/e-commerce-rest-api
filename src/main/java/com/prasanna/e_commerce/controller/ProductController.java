package com.prasanna.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.service.ProductService;
import com.prasanna.e_commerce.service.UserService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private UserService userService;
	
	private ProductService productService;
	
	@Autowired
	public ProductController(ProductService productService) {
		this.productService=productService;
	}
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts(){
		List<Product> products=productService.getAllProducts();
		
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@GetMapping("/products/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id){
		Product product=productService.getProductById(id);

		if (product !=null) 
			return new ResponseEntity<>(product,HttpStatus.OK);
		else
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
				
	}
	
	@PostMapping("/admin/add")
	public ResponseEntity<?> addProduct(@RequestParam String username,@RequestBody Product product){
		User user=userService.getByUsername(username);
		
		if (!user.getRole().equalsIgnoreCase("ADMIN")) {
			return new ResponseEntity<>("Only admin can add products",HttpStatus.UNAUTHORIZED);
		}
		
		Product prod=productService.addProduct(product);
		return new ResponseEntity<>(prod,HttpStatus.CREATED);
	}
	
	@PutMapping("/admin/update/{id}")
	public ResponseEntity<?> updateProduct(@RequestParam String username,@PathVariable Long id, @RequestBody Product product) {
		User user=userService.getByUsername(username);
		
		if (!user.getRole().equalsIgnoreCase("ADMIN")) {
			return new ResponseEntity<>("Only admin can update products",HttpStatus.UNAUTHORIZED);
		}
		Product prod=productService.updateProduct(id,product);
		return new ResponseEntity<>(prod, HttpStatus.OK);
	}
	
	@DeleteMapping("/admin/delete/{id}")
	public ResponseEntity<?> deleteProduct(@RequestParam String username, @PathVariable Long id){
		User user=userService.getByUsername(username);
		
		if (!user.getRole().equalsIgnoreCase("ADMIN")) {
			return new ResponseEntity<>("Only admin can delete products",HttpStatus.UNAUTHORIZED);
		}
		
		productService.deleteProduct(id);
		return new ResponseEntity<>("Product deleted", HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public List<Product> searchProduct(@RequestParam String keyword){
		return productService.searchProduct(keyword);
	}
	
}
