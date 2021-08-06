package com.example.jpa.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class ShippingAddress implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String address;
	
	@OneToOne(mappedBy = "deliveryAddress")
	Customer customer;
	
	

	@Override
	public String toString() {
		return "ShippingAddress [id=" + id + ", address=" + address + "]";
	}
	
	
	
	
}
