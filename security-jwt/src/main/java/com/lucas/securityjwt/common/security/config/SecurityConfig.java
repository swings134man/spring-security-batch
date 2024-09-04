package com.lucas.securityjwt.common.security.config;

import com.lucas.securityjwt.common.filter.AuthoritiesLoggingAfterFilter;
import com.lucas.securityjwt.common.filter.AuthoritiesLoggingAtFilter;
import com.lucas.securityjwt.common.filter.CsrfCookieFilter;
import com.lucas.securityjwt.common.filter.RequestValidationBeforeFilter;
import com.lucas.securityjwt.common.security.jwt.filter.JwtTokenGeneratorFilter;
import com.lucas.securityjwt.common.security.jwt.filter.JwtTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.Collections;

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
        // CSRF Handler
        CsrfTokenRequestAttributeHandler requestHandler = new CsrfTokenRequestAttributeHandler();
        requestHandler.setCsrfRequestAttributeName("_csrf"); // Default: _csrf (Spring Security)

        http
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // JSESSIONID Not Used
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
                .csrf((csrf) -> csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/contact", "/register") // CSRF Token Ignore: 인증없이 가능한 요청
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                )
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) // CSRF Token 생성후, 전달
                .addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class) // Basic Auth 이전에 실행되는 Custom Filter
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class) // Basic Auth 와 같은 위치에 실행되는 Custom Filter
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class) // Basic Auth 이후에 실행되는 Custom Filter
                .addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class) // 인증완료 후 JWT 발급
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class) // 실제 인증 유효성 검사 이전 JWT 검증
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/myAccount").hasRole("USER")
                                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/myLoans").authenticated()
                                .requestMatchers("/myCards").hasRole("USER")
                                .requestMatchers("/user").authenticated()
                                .requestMatchers("/notices", "/contact", "/register").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }


}
