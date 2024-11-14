package com.lucas.bomkey.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class PageController {


//    @GetMapping("/login")
//    public String loginController() {
//        return "login";
//    }

    @GetMapping("/signup")
    public String signupController() {
        return "signup";
    }

}
