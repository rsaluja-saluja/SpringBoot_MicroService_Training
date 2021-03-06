package b35.banking.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import b35.banking.dto.FundTransfer;
import b35.banking.entiry.Account;
import b35.banking.entiry.Customer;
import b35.banking.repo.AccountRepository;
import b35.banking.repo.CustomerRepository;

@Service
@Transactional
public class BankService {

	@Autowired
	CustomerRepository custRepo;
	
	@Autowired
	AccountRepository acctRepo;
	
	int accId=0;
	
	public Account createAccount(Customer customer, String accType)	 {

		if(!(accType.equalsIgnoreCase("Current") || accType.equalsIgnoreCase("Saving")))
				return null;
		Account acc = new Account();
		acc.setAccountType(accType);		
		acc = acctRepo.save(acc);
		
		String custId = getCustomerId(customer.getName().getFirstName());
		customer.setId(custId);
		customer.setAccount(acc);
		Customer cust = custRepo.save(customer);
		
		return acc;
		
	}
	
	public Account createJointAccount(Customer customer1, Customer customer2, String accType)	 {
		
		if(!accType.equalsIgnoreCase("Joint"))
			return null;
		
		Account acc = new Account();
		acc.setBalance(0);
		acc.setAccountType(accType);		
		acc = acctRepo.save(acc);
		
		String custId1 = getCustomerId(customer1.getName().getFirstName());
		customer1.setId(custId1);
		customer1.setAccount(acc);
		custRepo.save(customer1);
		
		String custId2 = getCustomerId(customer2.getName().getFirstName());
		customer2.setId(custId2);
		customer2.setAccount(acc);
		custRepo.save(customer2);
		
		return acc;
		
	}
	
	public Customer editCustomerDetails(Customer customer) {
		
		Customer cust = custRepo.findById(customer.getId());
		if(cust != null) {
			cust.setAddress(customer.getAddress());
			cust.setName(customer.getName());
			cust = custRepo.save(cust);
		}
		
		return cust;
	}
	
	public Account addAmountToAccount(int accId, double amount) {
		Account acc = acctRepo.findById(accId).get();
		if(acc != null) {
			acc.setBalance(acc.getBalance()+amount);
			acctRepo.save(acc);
		}
		return acc;
	}
	
	public List<Customer> getAllCustomers(Account acc) {
		List<Customer> customers = null;
		customers = custRepo.findByAccount(acc);
		return customers;
	}
	
	public List<Customer> getAllCustomersByAccId(int accId) {
		List<Customer> customers = null;
		Optional<Account> acc = acctRepo.findById(accId);
		if(!acc.isEmpty()) {			
			customers = custRepo.findByAccount(acc.get());
		}
		return customers;
	}
	
	public List<Account> getAllAccounts() {
		return acctRepo.findAll();
		
	}
	
	public Account getAccountById(int accId) {
		Account acc = acctRepo.findById(accId).get();
		return acc;		
	}
	
	public Customer getCustomer(String custId) {
		Customer cust = custRepo.findById(custId);
		
		return cust;		
	}

	public boolean fundTransfer(int fromAccId, int toAccId, double amount, StringBuilder str) {
		
		Optional<Account> fromAccount = acctRepo.findById(fromAccId);
		if(fromAccount.isEmpty()) {
			str.append("From Account with id "+fromAccId+" doesn't exist");
			return false;
		}
		if(fromAccount.get().getBalance() < amount)
			{
				str.append("Account with id "+fromAccId+" doesn't have sufficient balance. Current Balance is "+fromAccount.get().getBalance());
				return false;
			}
			
		
		Optional<Account> toAccount = acctRepo.findById(toAccId);
		if(toAccount.isEmpty())
		{
			str.append("To Account with id "+toAccId+" doesn't exist");
			return false;
		}
		
		Account fromAcc = fromAccount.get();
		Account toAcc = toAccount.get();
		
		fromAcc.setBalance(fromAcc.getBalance()-amount);
		toAcc.setBalance(toAcc.getBalance()+amount);
		
		acctRepo.save(fromAcc);
		acctRepo.save(toAcc);
		str.append("Account with id "+toAccId+" is credited with amount "+amount+". Current Balance: "+toAcc.getBalance()+". ");
		
		str.append("Account with id "+fromAccId+" is debited with amount "+amount+". Current Balance: "+fromAcc.getBalance());
		return true;
	}
	
	private String getCustomerId(String firstName) {
		Random rand = new Random();
		return firstName.substring(0, 2)+(rand.nextInt(10000)+1000);		
	}
	
	public void clearDatabase( ) {
		acctRepo.deleteAll();		
		custRepo.deleteAll();
	}
		
}
