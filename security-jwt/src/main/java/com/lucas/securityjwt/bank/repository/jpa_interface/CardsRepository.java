package com.lucas.securityjwt.bank.repository.jpa_interface;

import java.util.List;

import com.lucas.securityjwt.bank.model.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CardsRepository extends JpaRepository<Cards, Long> {
	
	List<Cards> findByCustomerId(int customerId);

}
