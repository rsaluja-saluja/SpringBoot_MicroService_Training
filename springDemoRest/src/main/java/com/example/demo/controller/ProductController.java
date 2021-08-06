package com.example.demo.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.pojo.Product;
import com.example.demo.service.ProductInterface;
import com.example.demo.utility.CustomErrorType;

@RestController
public class ProductController {
	
	@Autowired
	ProductInterface productService;
	
	@GetMapping("/getMessage")
	public ResponseEntity<String> getMessage() {
		String response=productService.getMessage();
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<?> createProduct(@RequestBody Product product) {
		ResponseEntity<?> response = new ResponseEntity<CustomErrorType>(
										new CustomErrorType("Unable to create with given Product name( "+product.getName()+") already exists"),
										HttpStatus.CONFLICT);
		System.out.println("createProduct Called");
		if(!productService.isProductExists(product)) {
			System.out.println("Product not already exists");
			Product saveproduct=productService.saveProduct(product);
			response=new ResponseEntity<>(saveproduct,HttpStatus.CREATED);
		}
		return response;
		
	}
	
	@GetMapping("/readbyid/{id}")
	public ResponseEntity<?> readById(@PathVariable int id) {
		ResponseEntity<?> response = new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No product Exists with id ("+id+")"),
				HttpStatus.NOT_FOUND);
		Product product = productService.findById(id);
		if(product != null) {
			response = new ResponseEntity<>(product,HttpStatus.FOUND);
		}
		
		return response;
		
	}
	
	@GetMapping("/readbyname/{name}")
	public ResponseEntity<?> readByName(@PathVariable String name) {
		ResponseEntity<?> response = new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No product Exists with name ("+name+")"),
				HttpStatus.NOT_FOUND);
		Product product = productService.findByName(name);
		if(product != null) {
			response = new ResponseEntity<>(product,HttpStatus.FOUND);
		}
		
		return response;
		
	}
	
	@GetMapping("/read/all")
	public ResponseEntity<?> readAllProducts() {
		ResponseEntity<?> response =  new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No Products found"),
				HttpStatus.NOT_FOUND);
		
		List<Product> products = productService.findAllProducts();
		if(!products.isEmpty()) {
			response = new ResponseEntity<>(products,HttpStatus.OK);
		}
		return response;
		
	}
	
	//Update existing product if provided product name is same
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable int id,@RequestBody Product product) {
		ResponseEntity<?> response = new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No product Exists with id ("+id+")"),
				HttpStatus.NOT_FOUND);
		Product prod = productService.findById(id);
				
		if(prod != null ) {
			if(prod.getName().equals(product.getName())) {
				product.setId(id);
				productService.updateProduct(product);
				response = new ResponseEntity<>(prod, HttpStatus.OK);
			}
			else
				response = new ResponseEntity<CustomErrorType>(
						new CustomErrorType("Product name for id: "+id+" is different. Existing Name: "+prod.getName()+" Provided Name: "+product.getName()),
						HttpStatus.CONFLICT);
		}
		return response;
		
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		ResponseEntity<?> response =  new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No Products found with id "+id),
				HttpStatus.NOT_FOUND);
		Product product = productService.deleteProductById(id);
		if(product != null) {
			response = new ResponseEntity<>(product, HttpStatus.OK);
		}
		return response;
		
	}
	@DeleteMapping("delete/all")
	public ResponseEntity<?> delete() {
		ResponseEntity<?> response =  new ResponseEntity<CustomErrorType>(
				new CustomErrorType("No Products Found."),
				HttpStatus.NOT_FOUND);
		List<Product> productList = productService.deleteAllProducts();
		if(!productList.isEmpty()) {
			response = new ResponseEntity<>(productList,HttpStatus.OK);
		}
		return response;
		
	}
}
