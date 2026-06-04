package com.prasanna.e_commerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.prasanna.e_commerce.model.CartItem;
import com.prasanna.e_commerce.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/view")
	public ResponseEntity<List<CartItem>> viewCart(){
		return ResponseEntity.ok(cartService.viewCart());
	}
	
	@PostMapping("/add/{id}")
	public ResponseEntity<String> addProductToCart(@PathVariable Long id) {
		return ResponseEntity.ok(cartService.addProductToCart(id));
	}

	@DeleteMapping("/remove/{id}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable Long id){
		return ResponseEntity.ok(cartService.removeProductFromCart(id));
	}
	
}
