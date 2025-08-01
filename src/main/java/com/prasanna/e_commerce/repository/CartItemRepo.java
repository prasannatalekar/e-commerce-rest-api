package com.prasanna.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prasanna.e_commerce.model.CartItem;
import com.prasanna.e_commerce.model.User;

@Repository
public interface CartItemRepo extends JpaRepository<CartItem, Long> {
	List<CartItem> findByUser(User user);
}
