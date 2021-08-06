package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Employee;

@SpringBootApplication
public class SpringDemoControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDemoControllerApplication.class, args);
	}

	@Bean
	CommandLineRunner myRun(Employee emp) {
		return (args)-> {
			System.out.println(emp.toString());
			System.out.println(emp.getName());
			System.out.println(emp.getEmail());
			System.out.println(emp.getStrengths());
			emp.setName("ABC");
			System.out.println(emp.getName());

		};
	}
}
