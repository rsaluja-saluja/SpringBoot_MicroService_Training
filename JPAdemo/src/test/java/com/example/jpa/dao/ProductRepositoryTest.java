package com.example.jpa.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.entity.Product;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepo;
//	
//	private static Product p1;
//	private static Product p2;
//	private static Product p3;
//	private static Product p4;
//	private static Product p5;
//	
	public static void init() {
//		p1 = new Product("TV", 23476.5f, "Sony", "India");
//		p2 = new Product("SmartTV", 23476.5f, "LG", "Japan");
//		p3 = new Product("cellPhone", 23476.5f, "OnePlus", "China");
//		p4 = new Product("Purifier", 23476.5f, "Kenstart", "Korea");
//		p5 = new Product("cell2", 23476.5f, "OnePlus", "China");
	}
	
	@Test
	@Order(1)
	void findAllWhenEmpty() {
		List<Product> products = productRepo.findAll();
		assertEquals(0, products.size());
	}
	
	@Test
	@Order(2)
	void findAllWhenRecordsExists() {
		productRepo.save(new Product("TV", 23476.5f, "Sony", "India"));
		productRepo.save(new Product("SmartTV", 23476.5f, "LG", "Japan"));
		productRepo.save(new Product("cellPhone", 23476.5f, "OnePlus", "China"));
		
		List<Product> products = productRepo.findAll();
		assertEquals(3, products.size());
	}
	
	@Test
	@Order(3)
	void testFindByBrand( ) {
		Product product = productRepo.findByBrand("Sony");
		assertEquals(product.getMadein(), "India");
	}



//	@Test
//	void testFindByMadein() {
//	}
//
//	@Test
//	void testUpdateNameUsingID() {
//	}
//
//	@Test
//	void testReplaceProductName() {
//	}
//
//	@Test
//	void testDeleteProductName() {
//	}

}
