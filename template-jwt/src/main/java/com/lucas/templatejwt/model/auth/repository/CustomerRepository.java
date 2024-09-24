package com.lucas.templatejwt.model.auth.repository;

import com.lucas.templatejwt.model.auth.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);
}
