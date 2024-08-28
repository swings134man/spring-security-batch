package com.lucas.securitybasic.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LoansController - Bank UI Test Controller
 * 대출 정보
 */
@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public String getLoanDetails() {
        return "loan details from DB";
    }
}
