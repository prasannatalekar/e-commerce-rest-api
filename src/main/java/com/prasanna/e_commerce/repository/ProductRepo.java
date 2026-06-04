package com.prasanna.e_commerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.prasanna.e_commerce.model.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

	List<Product> findByIsActiveTrue();
	
	@Query("SELECT p FROM Product p WHERE p.isActive = true AND " +
		       "(LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%')))")
		List<Product> searchProduct(@Param("keyword") String keyword);
	
	@Query("SELECT p FROM Product p WHERE " +
		       "LOWER(p.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
		       "LOWER(p.category) LIKE LOWER(CONCAT('%', :keyword, '%'))")
		List<Product> searchAllProducts(@Param("keyword") String keyword);
	
}
