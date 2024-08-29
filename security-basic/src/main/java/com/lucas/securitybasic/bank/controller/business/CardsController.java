package com.lucas.securitybasic.bank.controller.business;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CardsController - Bank UI Test Controller
 * 카드 정보
 */
@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getCardDetails() {
        return "card details from DB";
    }
}
