package com.example.jpa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.jpa.entity.Product;

public interface ProductRepository extends JpaRepository <Product, Integer> {

	Product findByBrand(String brand);
	List<Product> findByMadein(String madein);
	
	@Modifying
	@Query("UPDATE Product p SET p.name= :name WHERE p.id= :id")
	int updateNameUsingID(@Param("id") int id, @Param("name") String name);
	
	@Modifying
	@Query("UPDATE Product p SET p.name= :name WHERE p.name= :basename")
	int replaceProductName(@Param("name") String name, @Param("basename") String basename);
	
	@Modifying
	@Query("DELETE Product p WHERE p.name= :name")
	int deleteProductName(@Param("name") String name);
}
