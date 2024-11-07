package com.lucas.bomkey.rest;

import com.lucas.bomkey.user.User;
import com.lucas.bomkey.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User res = userService.saveUser(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String email) {
        boolean res = userService.deleteByUserEmail(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
