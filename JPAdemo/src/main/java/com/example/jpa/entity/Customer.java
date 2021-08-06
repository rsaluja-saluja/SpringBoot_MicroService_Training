package com.example.jpa.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity

public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String name; 
	
	@OneToOne(cascade= CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="fk+ship_address")
	ShippingAddress deliveryAddress;

	public Customer() {
		super();
	}

	public Customer(int id, String name, ShippingAddress deliveryAddress) {
		super();
		this.id = id;
		this.name = name;
		this.deliveryAddress = deliveryAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ShippingAddress getDeliveryAddress() {
		return deliveryAddress;
	}

	public void setDeliveryAddress(ShippingAddress deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
	
	
	
	
}
