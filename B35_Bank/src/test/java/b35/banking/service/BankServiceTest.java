package b35.banking.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import b35.banking.entiry.Account;
import b35.banking.entiry.Customer;
import b35.banking.entiry.Name;
import b35.banking.repo.AccountRepository;
import b35.banking.repo.CustomerRepository;

@SpringBootTest

class BankServiceTest {
	
	@Autowired
	BankService service;
	
	
	@BeforeEach
	public void after() {
		service.clearDatabase();
	}
	
	@Test
	void testCreateAccount() {	
		
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		
		Account acc1 = service.createAccount(cust1, "saving");
		assertEquals(acc1.getBalance(), 0.0);
		assertEquals(acc1.getAccountType(), "saving");
				
		Account acc2 = service.createAccount(cust1, "current");
		assertEquals(acc2.getBalance(), 0.0);
		assertEquals(acc2.getAccountType(), "current");
		
		assertEquals(2, service.getAllAccounts().size());
	}
	
	@Test
	void testCreateAccountWithWrongAccType() {

		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		
		Account acc1 = service.createAccount(cust1, "save");
		
		assertNull(acc1);
		assertEquals(service.getAllAccounts().size(), 0);
	}

	@Test
	void testCreateJointAccount() {

		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		
		Account acc1 = service.createJointAccount(cust1, cust2, "joint");
		
		List<Customer> customers = service.getAllCustomers(acc1);
			
		assertEquals(customers.size(), 2);
		
		List<Customer> customersList = service.getAllCustomersByAccId(acc1.getId());
			
		assertEquals(customersList.size(), 2);
	}
	
	@Test
	void testCreateJointAccountWithWrongAccType() {

		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		
		Account acc = service.createJointAccount(cust1, cust2, "saving");
		assertNull(acc);
	}
	
		
	@Test
	void testEditCustomerDetails() {
		//Customer cust = new Customer(new Name("first1","mid1","last1"),"street1",null);
		Customer cust = new Customer(new Name("first1","mid1","last1"),"street1");
		Account acc = service.createAccount(cust, "saving");
		
		List<Customer> customers = service.getAllCustomersByAccId(acc.getId());
		assertEquals(customers.size(), 1);
		assertEquals(customers.get(0).getAddress(),"street1");
		
		Customer custChange = new Customer(customers.get(0).getId(),new Name("first1","mid1","last1"),"street2");
		Customer cust1 = service.editCustomerDetails(custChange);
		
		List<Customer> customersList = service.getAllCustomersByAccId(acc.getId());
		assertEquals(customersList.size(), 1);
		assertEquals(customersList.get(0).getAddress(),"street2");
	}

	@Test
	void testEditCustomerDetailsWhenCustomerNotExists() {
		
		Customer cust = new Customer("fi3856",new Name("first1","mid1","last1"),"street2");
		Customer custChanged = service.editCustomerDetails(cust);
		assertNull(custChanged);	
	}
	
	@Test
	void testGetAllCustomers() {
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		
		Account acc1 = service.createJointAccount(cust1, cust2, "Joint");
		
		List<Customer> customers = service.getAllCustomers(acc1);
			
		assertEquals(customers.size(), 2);
	}
	
	@Test
	void testAddAmountToAccount() {
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");
		
		Account acc1 = service.createAccount(cust1, "saving");
		assertEquals(acc1.getBalance(), 0.0);
		assertEquals(acc1.getAccountType(), "saving");
		
		Account acc = service.addAmountToAccount(acc1.getId(), 5000);
		assertEquals(acc.getBalance(), 5000);
		
		assertEquals(service.getAllAccounts().size(), 1);
		assertEquals(service.getAllAccounts().get(0).getBalance(), 5000);
	}

	@Test
	void testFundTransfer() {
		//Create Account 1 with balance 5000 
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");		
		Account acc1 = service.createAccount(cust1, "saving");
		
		acc1 = service.addAmountToAccount(acc1.getId(), 5000);
		assertEquals(acc1.getBalance(), 5000);
		
		//Create account 2 with balance of 1000
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		Account acc2 = service.createAccount(cust2, "saving");
		
		acc2 = service.addAmountToAccount(acc2.getId(), 1000);
		assertEquals(acc2.getBalance(), 1000);
		
		assertEquals(service.getAllAccounts().size(), 2);
		
		StringBuilder str= new StringBuilder();
		boolean status = service.fundTransfer(acc1.getId(), acc2.getId(), 1000,str);
		assertEquals(status, true);
						
		assertEquals(service.getAccountById(acc1.getId()).getBalance(), 4000);
		assertEquals(service.getAccountById(acc2.getId()).getBalance(), 2000);
		
	}
	
	@Test
	void testFundTransferWhenFromAccNotExists() {
		
		//Create account 2 with balance of 1000
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		Account acc2 = service.createAccount(cust2, "saving");
		acc2 = service.addAmountToAccount(acc2.getId(), 1000);
		
		StringBuilder str= new StringBuilder();
		boolean status = service.fundTransfer(15, acc2.getId(), 1000,str);
		assertEquals(status, false);
	}
	
	@Test
	void testFundTransferWhenToAccNotExists() {
		
		//Create account 2 with balance of 1000
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");		
		Account acc1 = service.createAccount(cust1, "saving");		
		acc1 = service.addAmountToAccount(acc1.getId(), 5000);
		
		StringBuilder str= new StringBuilder();
		boolean status = service.fundTransfer(acc1.getId(), 12, 1000,str);
		assertEquals(status, false);
	}
	
	@Test
	void testFundTransferWhenInsufficientBalance() {
		
		//Create Account 1 with balance 5000 
		Customer cust1 = new Customer(new Name("first1","mid1","last1"),"street1");		
		Account acc1 = service.createAccount(cust1, "saving");
		acc1 = service.addAmountToAccount(acc1.getId(), 1000);
				
		//Create account 2 with balance of 1000
		Customer cust2 = new Customer(new Name("first2","mid2","last2"),"street2");
		Account acc2 = service.createAccount(cust2, "saving");
		acc2 = service.addAmountToAccount(acc2.getId(), 1000);
		
		StringBuilder str= new StringBuilder();
		boolean status = service.fundTransfer(acc1.getId(), acc2.getId(), 2000,str);
		assertEquals(status, false);
	}

}
