package com.example.jpa;

import java.util.List;
import java.util.Optional;

import javax.swing.CellEditor;
import javax.swing.CellRendererPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.jpa.dao.ProductRepository;
import com.example.jpa.entity.Product;

@SpringBootApplication
public class JpAdemoApplication {

	@Autowired
	ProductRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(JpAdemoApplication.class, args);
	}

//	@Bean
//	CommandLineRunner myRun() {
//		return (args)-> {
//			System.out.println(repo);
//			
//			Product tv = new  Product("TV", 2345.678f, "sony", "Japan");
//			Product cellPhone = new  Product("cellPhone", 2345.678f, "oneplus", "China");
//			Product purifier = new  Product("purifier", 2345.678f, "Phillips", "korea");
//			
//			repo.save(tv);
//			repo.save(cellPhone);
//			repo.save(purifier);
//			
//			List<Product> products = repo.findAll();
//			System.out.println("###############");
//			System.out.println(products);
//			System.out.println("###############");
//			
//			Optional<Product> product = repo.findById(2);
//			System.out.println("Product with id 2: "+ product);
//			
//		};
//	}
}
