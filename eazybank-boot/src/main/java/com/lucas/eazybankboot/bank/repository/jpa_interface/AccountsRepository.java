package com.lucas.eazybankboot.bank.repository.jpa_interface;

import com.lucas.eazybankboot.bank.model.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {
	
	Accounts findByCustomerId(int customerId);

}
