package b35.banking.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import b35.banking.entiry.Account;

@Repository
@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {


}
