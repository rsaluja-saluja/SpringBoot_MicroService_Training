package com.example.demo.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Configuration

public class Employee {

	@Value("${employee.name}")
	private String name;
	
	@Value("${employee.email}")
	private String email;
	
	@Value("${employee.strengths}")
	private List<String> strengths;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getStrengths() {
		return strengths;
	}

	public void setStrengths(List<String> strengths) {
		this.strengths = strengths;
	}
	

}
