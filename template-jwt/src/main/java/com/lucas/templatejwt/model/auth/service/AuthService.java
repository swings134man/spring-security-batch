package com.lucas.templatejwt.model.auth.service;

import com.lucas.templatejwt.model.auth.Customer;
import com.lucas.templatejwt.model.auth.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {

    private final CustomerRepository customerRepository;

    public Customer findByEmail(String email) {
        log.info("Finding customer by email: {}", email);
        return customerRepository.findByEmail(email);
    }

}
