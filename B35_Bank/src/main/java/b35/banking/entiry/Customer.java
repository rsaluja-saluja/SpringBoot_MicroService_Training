package b35.banking.entiry;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="customer")
public class Customer implements Serializable{

	@Id
	String id; // Use first 2 letters of customer FN + random  4 digit value 
	
	@Embedded
	Name name;
	String address;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="account_id",nullable=false)
	Account account;

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name.toString() + ", address=" + address + "]";
	}

	public Customer() {
		super();
	}

	public Customer(Name name, String address) {
		super();
		this.name = name;
		this.address = address;
		//this.account = account;
	}

	public Customer(String id, Name name, String address) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		//this.account = account;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
}
