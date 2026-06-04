package com.prasanna.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.exception.BadRequestException;
import com.prasanna.e_commerce.exception.ResourceNotFoundException;
import com.prasanna.e_commerce.model.CartItem;
import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.model.User;
import com.prasanna.e_commerce.repository.CartItemRepo;
import com.prasanna.e_commerce.repository.ProductRepo;
import com.prasanna.e_commerce.repository.UserRepo;

@Service
public class CartService {

		@Autowired
		private CartItemRepo cartRepo;
		
		@Autowired
		private UserRepo userRepo;
		
		@Autowired
		private ProductRepo productRepo;
		
		public List<CartItem> viewCart(){
			
			String username=SecurityContextHolder.getContext().getAuthentication().getName();
			User user=userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found!"));
			
			List<CartItem> cartItems=cartRepo.findByUser(user);
			
			if (cartItems.isEmpty()) 
				throw new BadRequestException("Your cart is empty!");
			
			return cartItems;
		}
		
		public String addProductToCart(Long productId) {
			
			String username=SecurityContextHolder.getContext().getAuthentication().getName();
			User user=userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found!"));
			
			Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found!"));

			if(!product.getIsActive())
				throw new BadRequestException("Product is not available!");
			
			if(product.getStockQuantity()<=0)
				throw new BadRequestException("Product is out of stock!");
				
			CartItem existingItem=cartRepo.findByUserAndProduct(user,product).orElse(null);
			
			if(existingItem != null) {
				existingItem.setQuantity(existingItem.getQuantity()+1);
				existingItem.setTotalAmount(existingItem.getQuantity() * product.getPrice());
				
				cartRepo.save(existingItem);
				return product.getName()+" quantity updated in cart.";
			}
			
			CartItem cartItem=new CartItem();
			cartItem.setUser(user);
			cartItem.setProduct(product);
			cartItem.setQuantity(1);
			cartItem.setTotalAmount(product.getPrice());
			
			cartRepo.save(cartItem);
			
			return product.getName() + " added to cart.";
		}
		
		public String removeProductFromCart(Long productId) {
			
			String username=SecurityContextHolder.getContext().getAuthentication().getName();
			User user=userRepo.findByUsername(username).orElseThrow(()->new ResourceNotFoundException("User not found!"));
			
			Product product=productRepo.findById(productId).orElseThrow(()->new ResourceNotFoundException("Product not found!"));

			CartItem cartItem=cartRepo.findByUserAndProduct(user,product).orElseThrow(()->new ResourceNotFoundException("Product not in cart!"));
				
			if(cartItem.getQuantity()>1) {
				cartItem.setQuantity(cartItem.getQuantity()-1);
				cartItem.setTotalAmount(cartItem.getQuantity() * product.getPrice());
				cartRepo.save(cartItem);
				return product.getName()+" quantity reduced in cart.";
			}
			
			cartRepo.delete(cartItem);
			
			return product.getName()+" removed from cart.";
		}
}
