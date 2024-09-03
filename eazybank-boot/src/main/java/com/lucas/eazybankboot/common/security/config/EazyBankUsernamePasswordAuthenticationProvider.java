package com.lucas.eazybankboot.common.security.config;

import com.lucas.eazybankboot.bank.model.entity.Authority;
import com.lucas.eazybankboot.bank.model.entity.Customer;
import com.lucas.eazybankboot.bank.repository.jpa_interface.CustomerJpaRepository;
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

/**
 * EazyBankUsernamePasswordAuthenticationProvider
 * Custom Authentication Provider
 *
 * authenticate() -> Username, Password 인증: 즉 User 정보, PW 비교 필요
 *
 * 해당 클래스가 존재하게 되면, DaoAuthenticationProvider 가 사용되지 않음. (Dao~ 에 들어갈 내용을 해당 클래스에서 재정의 사용 하기때문)
 * 즉 EazyBankUserDetails 가 사용되지 않음. (2024.09.02)
 *
 */
@Component
@RequiredArgsConstructor
public class EazyBankUsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final CustomerJpaRepository customerRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        Customer customer = customerRepository.findByEmail(username);

        if(customer != null) {
            if(passwordEncoder.matches(pwd, customer.getPwd())){
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

    // Username, Password 인증 -> DaoAuthenticationProvider 의 Default Code
    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
