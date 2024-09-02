package com.lucas.eazybankboot.bank.repository.jpa_interface;

import com.lucas.eazybankboot.bank.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}
