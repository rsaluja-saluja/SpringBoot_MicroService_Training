package b35.banking.dto;

import b35.banking.entiry.Customer;

public class JointAccount {
	Customer customer1;
	Customer customer2;
	String accType;
	public JointAccount() {
		super();
		// TODO Auto-generated constructor stub
	}
	public JointAccount(Customer customer1, Customer customer2, String accType) {
		super();
		this.customer1 = customer1;
		this.customer2 = customer2;
		this.accType = accType;
	}
	public Customer getCustomer1() {
		return customer1;
	}
	public void setCustomer1(Customer customer1) {
		this.customer1 = customer1;
	}
	public Customer getCustomer2() {
		return customer2;
	}
	public void setCustomer2(Customer customer2) {
		this.customer2 = customer2;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	

}
