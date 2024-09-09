package com.lucas.oauthboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SpringSecOAuth2GitHubConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(req -> req
                        .anyRequest().authenticated()
                )
                .oauth2Login(withDefaults());
        return http.build();
    }

    // Setting in yml file
//    @Bean
//    public InMemoryClientRegistrationRepository clientRepository() {
//        ClientRegistration clientReg = clientRegistration();
//        return new InMemoryClientRegistrationRepository(clientReg);
//    }
//
//    private ClientRegistration clientRegistration() {
//        return CommonOAuth2Provider.GITHUB.getBuilder("github")
//                .clientId("Ov23liRCTIs0BRYmIhWJ")
//                .clientSecret("")
//                .build();
//    }

}
