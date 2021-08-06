package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.pojo.Product;

@Service("productservice")
public class productImpl implements ProductInterface {

	private static HashMap<Integer,Product> products = new HashMap<>(); //Id is unique
	private static HashMap<String,Integer> idNameHashMap = new HashMap<>(); // Name is also unique, use this map to make search by name fast
	private static int num =0;
	
	@Override
	public Product findById(int id) {
		return products.get(id);
	}

	@Override
	public Product findByName(String name) {
		return products.get(idNameHashMap.get(name));
		
	}

	@Override
	public Product findByBrand(String brand) {
		return null;
	}

	@Override
	public List<Product> findAllProducts() {
		return new ArrayList(products.values());
	}

	@Override
	public Product saveProduct(Product product) {
		synchronized (this) {
			product.setId(getGeneratedId());
			products.put(product.getId(), product);
			idNameHashMap.put(product.getName(), product.getId());			
			System.out.println("No of products "+products.size());
			return product;
		}
	}

	@Override
	public void updateProduct(Product product) {
		products.put(product.getId(), product);
	}

	@Override
	public boolean isProductExists(Product product) {
//		Product foundProduct =  findById(product.getId());
//		if(foundProduct != null) {
//			return true;
//		}
//		return false;
		
		if(idNameHashMap.get(product.getName()) != null) 
			return true;
		else
			return false;
	}

	@Override
	public Product deleteProductById(int id) {
		Product product = products.remove(id);
		if(product != null) {
			System.out.println("product found with id "+id);
			idNameHashMap.remove(product.getName());
		}
		else
			System.out.println("product not found with id "+id);
		return product;
	}
	
	@Override
	public List<Product> deleteAllProducts() {
		List<Product> productList = new ArrayList<>(products.values());
		if(!products.isEmpty()) {
			
			System.out.println("products size: "+products.size());
			System.out.println("id map size: "+idNameHashMap.size());
			products.clear();
			idNameHashMap.clear();
			System.out.println("products size: "+products.size());
			System.out.println("id map size: "+idNameHashMap.size());
			
		}
		return productList;
	}


	@Override
	public String getMessage() {
		return "#### Welcome to Spring Rest Service ###";
	}
	
	private int getGeneratedId()
	{
		num=num+1;
		return num;
	}

	
}
