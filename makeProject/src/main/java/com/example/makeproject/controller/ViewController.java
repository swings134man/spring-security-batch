package com.example.makeproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/************
 * @info : 뷰(타임리프) 를 반환하는 컨트롤러
 * @name : ViewController
 * @date : 2022/12/19 7:52 PM
 * @author : SeokJun Kang(swings134@gmail.com)
 * @version : 1.0.0
 * @Description :
 ************/
@Controller
public class ViewController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/view/user")
    public @ResponseBody String user() {
        return "user";
    }

    @GetMapping("/view/manager")
    public @ResponseBody String manager() {
        return "manager";
    }

    @GetMapping("/view/admin")
    public @ResponseBody String admin() {
        return "admin";
    }

    // ---- 로그인, 가입
    // 스프링 시큐리티가 현재 dispatch 함.
    @GetMapping("/login")
    public @ResponseBody String login() {
        return "login";
    }

    @GetMapping("/join")
    public @ResponseBody String join() {
        return "join";
    }

    @GetMapping("/joinProc")
    public @ResponseBody String joinProc() {
        return "회원가입 왼료됨!";
    }

}
