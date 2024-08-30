package com.lucas.securitybasic.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

/**
 * SecurityConfig Class - Configuration class for Spring Security
 */
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}


    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Else: (csrf -> csrf.disabled()) Cause! 401 error on PostMan
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/notices", "/contact", "/register").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults()); // swagger error: Must be authenticated

        return http.build();
    }

    // For Swagger
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers(
//                "/swagger-ui/**",
//                "/swagger-config",
//                "/swagger-resources/**",
//                "/v3/api-docs/**"
//        );
//    }

}
