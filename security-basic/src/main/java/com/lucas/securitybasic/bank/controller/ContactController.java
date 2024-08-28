package com.lucas.securitybasic.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ContactController - Bank UI Test Controller
 * Contact us 수신 정보
 */
@RestController
public class ContactController {

    @GetMapping("/contact")
    public String saveContactInquiryDetails() {
        return "Inquiry details saved to DB";
    }
}
