package com.example.demo.demoFactory;

import org.springframework.stereotype.Service;

@Service("tata")
class Tata implements Car {

	@Override
	public void printInfo() {
		// TODO Auto-generated method stub
		System.out.println("##### Tata ####");
	}

}
