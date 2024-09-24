package com.lucas.templatejwt.common.security.config;

import com.lucas.templatejwt.model.auth.Authority;
import com.lucas.templatejwt.model.auth.Customer;
import com.lucas.templatejwt.model.auth.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username);

        if(customer != null){
            if(passwordEncoder.matches(pwd, customer.getPwd())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(customer.getAuthorities()));
            }else {
                throw new BadCredentialsException("Password Not Matched");
            }
        }else {
            throw new BadCredentialsException("User Not Found With the Given Details");
        }
    }

    // Set To List
    private List<GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for(Authority authority : authorities){
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getName()));
        }

        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
