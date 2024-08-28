package com.lucas.securitybasic.bank.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * NoticesController - Bank UI Test Controller
 * 공지사항 정보
 */
@RestController
public class NoticesController {

    @GetMapping("/notices")
    public String getNotices() {
        return "notices from DB";
    }
}
