package b35.banking.repo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b35.banking.entiry.Account;
import b35.banking.entiry.Customer;

@Repository
@Transactional
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

//	@Modifying
//	@Query
//	public updateCustomer() {
//		
//	}
	
	List<Customer> findByAccount(Account account);
	Customer findById(String id);
}
