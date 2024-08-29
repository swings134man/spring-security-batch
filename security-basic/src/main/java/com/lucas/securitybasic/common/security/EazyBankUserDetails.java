package com.lucas.securitybasic.common.security;

import com.lucas.securitybasic.bank.model.entity.Customer;
import com.lucas.securitybasic.bank.repository.jpa_interface.CustomerJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * EazyBankUserDetails Class - UserDetailsService implementation
 * for EazyBank application
 * Security 의 인가를 사용하기 위해서는, UserDetailsService 를 구현해야함.
 *
 * 해당 클래스는 @Component("userDetailsService") 로 수정가능 @Service 대신
 *  -> 이렇게 하면, SecurityConfig 에서 userDetailsService 를 주입받을 수 있음.
 *  -> 또한 User 의 정보를 User 관리 Service 에서 사용할 수 있음
 *
 * User.class 생성자의 값은, DaoAuthenticationProvider 에서 사용됨.
 *  -> DB 의 값인 PWD 와, client 가 요청한 값인 PWD 를 비교하기 위함.
 */
@Service
@RequiredArgsConstructor
public class EazyBankUserDetails implements UserDetailsService {

    private final CustomerJpaRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        String userName, password;
        List<GrantedAuthority> authorities = new ArrayList<>();
        Customer customerResult = customerRepository.findByEmail(username);

        if(customerResult == null) throw new UsernameNotFoundException("User not found");

        userName = customerResult.getEmail();
        password = customerResult.getPwd();
        authorities.add(new SimpleGrantedAuthority(customerResult.getRole())); // 현재는 1:1 (user:role) 관계.

        return new User(userName, password, authorities); // To Spring Security Framework
    }
}
