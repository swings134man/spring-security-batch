package com.lucas.bomkey.rest;

import com.lucas.bomkey.rest.dto.AuthCodeRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth")
public class OAuthController {


    @PostMapping("/v1/authorize")
    public String responseAuthCode(@RequestBody AuthCodeRequest request) {
        // user id,pw check
        // Gen Code
        // Save Code - server Memory
        return "authorize code";
    }

    @PostMapping("/v1/token")
    public void responseToken() {
        // AT,RT Response
    }
}
