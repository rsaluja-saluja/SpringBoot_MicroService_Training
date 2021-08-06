package com.example.jpa;

public class CustomErrorType {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CustomErrorType(String message) {
		super();
		this.message = message;
	}

	public CustomErrorType() {
		super();
	}
	
}
