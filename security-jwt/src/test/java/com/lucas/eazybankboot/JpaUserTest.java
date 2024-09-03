package com.lucas.eazybankboot;

import com.lucas.securityjwt.bank.model.entity.Customer;
import com.lucas.securityjwt.bank.repository.jpa_interface.CustomerJpaRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JpaUserTest {

    @Autowired
    private CustomerJpaRepository customerRepository;


    @Test
    @DisplayName("JPA User Test")
    void test1() {
        Customer result = customerRepository.findByEmail("happy@example.com");
        System.out.println("result = " + result.getAuthorities().toString());
    }

}
