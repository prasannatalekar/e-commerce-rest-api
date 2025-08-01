package com.prasanna.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.model.CartItem;
import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.repository.CartItemRepo;

@Service
public class CartService {

		@Autowired
		private CartItemRepo cartRepo;
		
		@Autowired
		private UserService userService;
	
		@Autowired
		private ProductService productService;
		
		public String addToCart(String username, Long productId) {
			User user=userService.getByUsername(username);
			Product product=productService.getProductById(productId);
			
			CartItem item=new CartItem();
			item.setUser(user);
			item.setProduct(product);
			cartRepo.save(item);
			
			return product.getName()+" : added to cart.";
		}
		
		public List<CartItem> viewCart(String username){
			User user=userService.getByUsername(username);
			List<CartItem> cartItems=cartRepo.findByUser(user);
			
			for(CartItem ci : cartItems) {
				ci.getUser().getUserId();
			}
			
			return cartItems;
		}
		
}
