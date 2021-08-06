package com.example.demo.service;

import java.util.List;

import com.example.demo.pojo.Product;

public interface ProductInterface {
	
	Product findById(int id);
	Product findByName(String name);
	Product findByBrand(String brand);

	List<Product> findAllProducts();
	
	Product saveProduct(Product product);
	void updateProduct(Product product);
	
	boolean isProductExists(Product product);
	Product deleteProductById(int id);
	
	List<Product> deleteAllProducts();
	
	String getMessage();
	

}
