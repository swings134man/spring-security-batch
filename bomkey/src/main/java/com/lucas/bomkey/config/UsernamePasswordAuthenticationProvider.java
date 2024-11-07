//package com.lucas.bomkey.config;
//
//import com.lucas.bomkey.user.User;
//import com.lucas.bomkey.user.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//@Component
//@RequiredArgsConstructor
//public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {
//
//    private final UserRepository repository;
//    private final BCryptPasswordEncoder encoder;
//
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//        User user = repository.findByUserName(username).get();
//
//        if(user != null) {
//            if (encoder.matches(pwd, user.getPassword())) {
//                return new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(username, pwd);
//            } else {
//                throw new org.springframework.security.authentication.BadCredentialsException("Password Not Matched");
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}
