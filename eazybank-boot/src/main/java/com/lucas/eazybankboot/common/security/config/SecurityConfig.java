package com.lucas.eazybankboot.common.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Collections;
import java.util.List;

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
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                            cors.setAllowedMethods(Collections.singletonList("*"));
                            cors.setAllowedHeaders(Collections.singletonList("*"));
                            cors.setAllowCredentials(true);
                            cors.setMaxAge(3600L); // 1 hour
                            return cors;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable) // Else: (csrf -> csrf.disabled()) Cause! 401 error on PostMan
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/notices", "/contact", "/register").permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }


}
