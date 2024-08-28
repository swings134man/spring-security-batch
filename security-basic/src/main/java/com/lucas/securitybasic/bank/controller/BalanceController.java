package com.lucas.securitybasic.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * BalanceController - Bank UI Test Controller
 * 잔고 정보
 */
@RestController
public class BalanceController {

    @GetMapping("/myBalance")
    public String getBalanceDetails() {
        return "balance details from DB";
    }


}
