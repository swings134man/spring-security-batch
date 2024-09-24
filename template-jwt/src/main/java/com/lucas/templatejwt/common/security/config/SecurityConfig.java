package com.lucas.templatejwt.common.security.config;

import com.lucas.templatejwt.common.security.filter.JwtGenFilter;
import com.lucas.templatejwt.common.security.filter.JwtValidateFilter;
import com.lucas.templatejwt.common.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@RequiredArgsConstructor
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private final JwtProvider provider;

    @Bean
    public JwtGenFilter JwtGenFilter() {
        return new JwtGenFilter(provider);
    }

    @Bean
    public JwtValidateFilter JwtValidateFilter() {
        return new JwtValidateFilter(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer
                        .configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                            cors.setAllowedMethods(Collections.singletonList("*"));
                            cors.setAllowedHeaders(Collections.singletonList("*"));
                            cors.setExposedHeaders(Arrays.asList("Authorization"));
                            cors.setAllowCredentials(true);
                            cors.setMaxAge(3600L); // 1 hour
                            return cors;
                        })
                )
                .csrf(AbstractHttpConfigurer::disable)
                .logout(logout -> logout.logoutUrl("/logout"))
                .addFilterBefore(JwtGenFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(JwtValidateFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request -> request
                                .requestMatchers("/login").permitAll()
                                .anyRequest().authenticated()
                        )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }
}
