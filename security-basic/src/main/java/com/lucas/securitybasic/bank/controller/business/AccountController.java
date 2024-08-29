package com.lucas.securitybasic.bank.controller.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * AccountController - Bank UI Test Controller
 * 계정 정보
 */
@RestController
public class AccountController {

    @GetMapping("/myAccount")
    public String getAccountDetails() {
        return "account details from DB";
    }

}
