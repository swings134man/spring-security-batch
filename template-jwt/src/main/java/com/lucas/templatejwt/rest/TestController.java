package com.lucas.templatejwt.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class TestController {


    @GetMapping("/test")
    public String testGet() {
        return "Test Get";
    }

    @PostMapping("/test")
    public String testPost(@RequestParam String name) {
        log.info("Name: {}", name);
        return name;
    }


}
