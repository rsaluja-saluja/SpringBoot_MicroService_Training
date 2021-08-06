package com.example.jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.jpa.dao.ProductRepository;
import com.example.jpa.entity.Product;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	ProductRepository repo;
	
	@InjectMocks
	ProductService service;
	
	
	private static Product p1;
	private static Product p2;
	private static Product p3;
	private static Product p4;
	private static Product p5;

	@BeforeAll
	public static void init() {
		p1 = new Product("TV", 23476.5f, "Sony", "India");
		p2 = new Product("SmartTV", 23476.5f, "LG", "Japan");
		p3 = new Product("cellPhone", 23476.5f, "OnePlus", "China");
		p4 = new Product("Purifier", 23476.5f, "Kenstart", "Korea");
		p5 = new Product("cell2", 23476.5f, "OnePlus", "China");
	}
	
	@Test
	void testWhenDBEmpty() {
		
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList());
		assertEquals(0, service.readAllProducts().size());
		//Mockito.verify(repo, Mockito.times(1));
	}
	
	@Test
	void testWhenDBIsNotEmpty() {
		List<Product> products = new ArrayList();
		Mockito.when(repo.findAll()).thenReturn(Arrays.asList(p1,p2,p3));
		assertEquals(3, service.readAllProducts().size());
		assertEquals(p1, service.readAllProducts().get(0));
		//Mockito.verify(repo, Mockito.times(1));
	}
	
//	@Test
//	void testReadproduct() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testReadAllProducts() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testSaveOrUpdate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDelete() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByBrand() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindByMadein() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEditProductNameUsingId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testEditProductName() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteProductByName() {
//		fail("Not yet implemented");
//	}

}
