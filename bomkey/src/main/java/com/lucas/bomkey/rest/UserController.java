package com.lucas.bomkey.rest;

import com.lucas.bomkey.user.User;
import com.lucas.bomkey.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /**
     * User Sign Up - For API
     * @param user
     * @return
     */
    @PostMapping("/signup")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User res = userService.saveUser(user);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * User Sign Up - For Page View
     * @param user
     * @return
     */
    @PostMapping("view/signup")
    public RedirectView test(User user) {
        User res = userService.saveUser(user);
        if(res != null) {
            return new RedirectView("/login");
        }else {
            return new RedirectView("/error");
        }
    }

    @DeleteMapping("/delete/{email}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable String email) {
        boolean res = userService.deleteByUserEmail(email);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

}
