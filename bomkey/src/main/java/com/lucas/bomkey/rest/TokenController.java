package com.lucas.bomkey.rest;

import com.lucas.bomkey.domains.user.redis.BlackListRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/************
 * @info : Custom Token Controller
 * @name : TokenController
 * @date : 2024. 11. 26. 오후 5:29
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description : Representative Method is "revokeToken"
 ************/
@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/oauth2")
public class TokenController {

    private final BlackListRedisService service;

    @PostMapping("/v1/revoke")
    public ResponseEntity<String> revokeToken(@RequestParam String token) {
        log.info("Token Revoke: {}", token);
        service.setToken(token);
        return ResponseEntity.ok("Token Has Expired");
    }
}
