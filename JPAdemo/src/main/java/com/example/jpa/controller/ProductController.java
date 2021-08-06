package com.example.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.jpa.CustomErrorType;
import com.example.jpa.entity.Product;
import com.example.jpa.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService service;
	
	@GetMapping("read/{id}")
	public ResponseEntity<?> readById(@PathVariable int id) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Product with id "+id+" not found"),HttpStatus.NOT_FOUND);
		Product product = service.readproduct(id);
		if(product != null) {
			response=ResponseEntity.ok(product);
		}
		return response;
	}
	
	@GetMapping("read/all")
	public ResponseEntity<?> readAllProducts() {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("No Prodcuts found"),HttpStatus.NOT_FOUND);
		List<Product> products = service.readAllProducts();
		if(!products.isEmpty()) {
			response=ResponseEntity.ok(products);
		}
		return response;
	}
}
