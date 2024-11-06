package com.lucas.bomkey.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUserByUsername(username);
        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
//                .disabled(!user.getEnabled())
//                .accountExpired(!user.getAccountNonExpired())
//                .accountLocked(!user.getAccountNonLocked())
//                .credentialsExpired(!user.getCredentialsNonExpired())
//                .authorities(user.getSimpleAuthorities())
                .build();
    }

    public User getUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
}
