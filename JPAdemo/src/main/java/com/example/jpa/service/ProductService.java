package com.example.jpa.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.jpa.dao.ProductRepository;
import com.example.jpa.entity.Product;

@Service
@Transactional
public class ProductService {

	@Autowired
	ProductRepository repo;
	
	public Product readproduct(int id) {
		java.util.Optional<Product> product = repo.findById(id);
		return product.get();
	}
	
	public List<Product> readAllProducts() {
		return repo.findAll();
	}
	
	public Product saveOrUpdate(Product product) {
		return repo.save(product);
	}
	
	public void delete(int id) {
		repo.deleteById(id);
	}
	public Product findByBrand(String brand) {
		return repo.findByBrand(brand);
	}
	
	public List<Product> findByMadein(String madein) {
		return repo.findByMadein(madein);
	}
	
	public int editProductNameUsingId(int id, String name) {
		return repo.updateNameUsingID(id, name);
	}
	
	public int editProductName(String oldName, String revisedName) {
		return repo.replaceProductName(oldName, revisedName);
	}
	
	public int deleteProductByName(String name) {
		return repo.deleteProductName(name);
	}
}
