package b35.banking.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import b35.banking.dto.Amount;
import b35.banking.dto.FundTransfer;
import b35.banking.dto.JointAccount;
import b35.banking.entiry.Account;
import b35.banking.entiry.Customer;
import b35.banking.service.BankService;
import b35.banking.utility.CustomErrorType;

@RestController
@RequestMapping("/bank")
public class BankController {

	@Autowired
	BankService service;
	
	@PostMapping("/createaccount/{accType}")
	public ResponseEntity<?> createNewAccount(@RequestBody Customer customer, @PathVariable String accType) {
		
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Customer with accountType "+accType+" not created"),HttpStatus.CONFLICT);

		Account acct = service.createAccount(customer, accType);
		if(acct != null) {
			response = ResponseEntity.ok(acct);
		}
		return response;
	}
	
	@PostMapping("/createjointaccount")
	public ResponseEntity<?> createJointAccount(@RequestBody JointAccount jointAccount) {
		
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Customers with accountType "+jointAccount.getAccType()+" not created"),HttpStatus.CONFLICT);

		Account acct = service.createJointAccount(jointAccount.getCustomer1(), jointAccount.getCustomer2(), jointAccount.getAccType());
		
		if(acct != null) {
			response = ResponseEntity.ok(acct);
		}
		return response;
	}
	
	
	@GetMapping("/getAllCustomersOfAccount/{id}")
	public ResponseEntity<?> getAllCustomerByAccId(@PathVariable int id) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Customer with account id "+id+" doesn't exists"),HttpStatus.NOT_FOUND);
		
		List<Customer> customers = service.getAllCustomersByAccId(id);
		if(customers != null )
			response = ResponseEntity.ok(customers);
		return response;
	}
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<?> editCustomer(@PathVariable String id) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Customer with id "+id+" doesn't exists"),HttpStatus.NOT_FOUND);
		Customer cust = service.getCustomer(id);
		
		if(cust != null) {
			response = ResponseEntity.ok(cust);
		}
		return response;
	}
	
	@PutMapping("/editCustomer")
	public ResponseEntity<?> editCustomer(@RequestBody Customer customer) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Customer with id "+customer.getId()+" doesn't exists"),HttpStatus.NOT_FOUND);
		
		Customer cust = service.editCustomerDetails(customer);
		if(cust != null) {
			response = ResponseEntity.ok(customer);
		}
		return response;
	}
	
	@PutMapping("/addAmount/{accId}")
	public ResponseEntity<?> addAmountToAccount(@PathVariable int accId, @RequestBody Amount amount) {
		ResponseEntity<?> response = new ResponseEntity<>(new CustomErrorType("Account with id "+accId+" doesn't exists"),HttpStatus.NOT_FOUND);
		Account acc = service.addAmountToAccount(accId, amount.getAmount());
		if(acc != null) {
			response = ResponseEntity.ok(acc);
		}
		return response;
	}
	
	@PostMapping("/transfer")
	public ResponseEntity<?> doFundTransfer(@RequestBody FundTransfer fundTransfer) {
		StringBuilder str= new StringBuilder();
		ResponseEntity<?> response; 
		boolean status = service.fundTransfer(fundTransfer.getFromAccountId(), fundTransfer.getToAccountId(), fundTransfer.getAmount(),str);
		if(status) 
			response = new ResponseEntity<>(new CustomErrorType(str.toString()),HttpStatus.OK);
		else 
			response = new ResponseEntity<>(new CustomErrorType(str.toString()),HttpStatus.CONFLICT);
		
		return response;
	}
}
