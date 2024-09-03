package com.lucas.securityjwt.bank.repository.jpa_interface;

import com.lucas.securityjwt.bank.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJpaRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}
