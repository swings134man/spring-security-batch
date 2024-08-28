package com.example.makeproject.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FirstController {

//    @GetMapping("/")
//    public String test() {
//        return "First Controller";
//    }

    @GetMapping("/showMe")
    public List<String> hello() {
        return Arrays.asList("첫번째 인사", "두번째 인사");
    }

    @GetMapping("/msg")
    public String message() {
        return "안녕하세요!";
    }

}
