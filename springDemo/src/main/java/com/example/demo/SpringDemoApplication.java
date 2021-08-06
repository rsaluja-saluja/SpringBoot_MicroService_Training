package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.demoFactory.Car;
import com.example.demo.demoFactory.CarFactory;

//Component Scan is not required if all classes/interfaces are defined 
//in default package or any package in that default package 

//With Autowired maruti and tata classed need not to be public.

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.demoFactory"})
public class SpringDemoApplication {

	@Autowired
	@Qualifier("maruti")
	Car maruti;
	
	@Autowired
	@Qualifier("tata")
	Car tata;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringDemoApplication.class, args);
		
//		Car maruti = CarFactory.getMarutiObject();
//		Car tata = CarFactory.getTataObject();
		
//		
//		maruti.printInfo();
//		tata.printInfo();
		
	}
	
	@Bean
	CommandLineRunner myRun() {
		return (args)-> {
			maruti.printInfo();
			tata.printInfo();
		};
	}

}
