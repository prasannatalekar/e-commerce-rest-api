package com.prasanna.e_commerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prasanna.e_commerce.model.Product;
import com.prasanna.e_commerce.repository.ProductRepo;

@Service
public class ProductService {

	private ProductRepo productRepo;
	
	@Autowired
	public ProductService(ProductRepo productRepo) {
		this.productRepo=productRepo;
	}
	
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product getProductById(Long id) {
		return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with id : "+id));
	}

	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	public Product updateProduct(Long id, Product product) {
		Product prod=getProductById(id);
			prod.setName(product.getName());
			prod.setCategory(product.getCategory());
			prod.setDescription(product.getDescription());
			prod.setPrice(product.getPrice());
			prod.setStock(product.getStock());
			
			return productRepo.save(prod);	
	}

	public void deleteProduct(Long id) {
		if (!productRepo.existsById(id)) {
			throw new RuntimeException("Product not found with id : "+id);
		}
		productRepo.deleteById(id);
	}
	
	public List<Product> searchProduct(String keyword){
		return productRepo.searchProduct(keyword);
	}
	
}
