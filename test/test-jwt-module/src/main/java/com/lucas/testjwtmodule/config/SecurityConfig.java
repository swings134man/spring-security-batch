package com.lucas.testjwtmodule.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableWebSecurity(debug = false)
@Slf4j
public class SecurityConfig {

    private static final String JWK_SET_URI = "http://localhost:9999/oauth2/jwks";

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Collections.singletonList("http://localhost:9000"));
        config.setAllowedMethods(Collections.singletonList("*"));
        config.setAllowedHeaders(Collections.singletonList("*"));
        config.setExposedHeaders(Arrays.asList("Authorization"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L); // 1 hour

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(Customizer.withDefaults()) // JWT 토큰 검증
                );

        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws Exception {
        try {
            return NimbusJwtDecoder.withJwkSetUri(JWK_SET_URI).build();
        } catch (Exception e) {
            log.error("JWT Decoder initialization failed", e);
            throw e;
        }
    }

    // Public Key 를 URL 로 가져오므로 패스...
//    private RSAPublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {
//        // 인증 서버에서 제공한 공개 키를 반환 (JWK 공개 키 URL로 가져오거나 하드코딩)
//        // 예: 인증 서버에서 JWK URL 가져오기 -> "http://auth-server.example.com/.well-known/jwks.json"
//        String publicKeyPEM = "-----BEGIN PUBLIC KEY----- ... -----END PUBLIC KEY-----";
//        return (RSAPublicKey) KeyFactory.getInstance("RSA")
//                .generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKeyPEM)));
//    }
}
