package com.lucas.securitybasic.bank.controller;

import com.lucas.securitybasic.bank.model.entity.Customer;
import com.lucas.securitybasic.bank.repository.jpa_interface.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    // TODO: Business Logic Will Be Moved To Service Class

    @PostMapping("/register")
    public ResponseEntity<String> registCustomer(@RequestBody Customer customer) {
        ResponseEntity<String> response = null;

        try {
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
}
