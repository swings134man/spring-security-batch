package com.lucas.securityjwt.bank.controller;

import com.lucas.securityjwt.bank.model.entity.Customer;
import com.lucas.securityjwt.bank.repository.jpa_interface.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

/**
 * Customer - Controller
 * 고객 정보
 * 고객과 관련된. login, 생성, 수정, 삭제, 조회 ... ETC
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerJpaRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    // TODO: Business Logic Will Be Moved To Service Class

    @PostMapping("/register")
    public ResponseEntity<String> registCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;

        try {
            // Encrypt Password
            customer.setPwd(passwordEncoder.encode(customer.getPwd()));
            customer.setCreateDt(String.valueOf(new Date(System.currentTimeMillis())));

            Customer saveResult = customerRepository.save(customer);
            if(saveResult.getId() > 0){
                log.info("Customer Results: {}", saveResult);
                response = ResponseEntity.status(HttpStatus.CREATED).body("Customer Registration Success");
            }
        } catch (Exception e) {
            response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Customer Registration Failed: " + e.getMessage());
        }

        return response;
    }


    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Customer result = customerRepository.findByEmail(authentication.getName());
        if (result != null) {
            return result;
        } else {
            return null;
        }

    }
}
