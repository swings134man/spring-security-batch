package com.lucas.securityjwt.bank.repository.jpa_interface;

import java.util.List;

import com.lucas.securityjwt.bank.model.entity.Loans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LoanRepository extends JpaRepository<Loans, Long> {
	
	List<Loans> findByCustomerIdOrderByStartDtDesc(int customerId);

}
