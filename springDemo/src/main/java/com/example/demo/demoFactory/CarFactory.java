package com.example.demo.demoFactory;

public class CarFactory {
	
	public static Car getMarutiObject() {
		return new Maruti();
	}
	
	public static Car getTataObject() {
		return new Tata();
	}

}
