package com.lucas.socialsample.infra.security

import com.lucas.socialsample.infra.configs.JwtProperties
import com.lucas.socialsample.infra.configs.SecurityConfigProperties
import com.lucas.socialsample.models.auth.service.LocalUserDetailsService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableWebSecurity
@EnableConfigurationProperties(value = [JwtProperties::class, SecurityConfigProperties::class])
class SecurityConfig {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        jwtAuthenticationFilter: JwtAuthenticationFilter,
        oauth2LoginSuccessHandler: OAuth2LoginSuccessHandler,
        oauth2LoginFailureHandler: OAuth2LoginFailureHandler,
        securityConfigProperties: SecurityConfigProperties,
        authenticationProvider: DaoAuthenticationProvider,
    ): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .headers { headers -> headers.frameOptions { it.disable() } }
            .cors(Customizer.withDefaults())
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .authorizeHttpRequests { auth ->
                auth.requestMatchers(*securityConfigProperties.publicPaths.toTypedArray()).permitAll()
                auth.requestMatchers(
                    HttpMethod.POST,
                    "/api/auth/signup",
                    "/api/auth/login",
                    "/api/auth/refresh",
                    "/api/auth/logout",
                    "/api/auth/social/complete",
                ).permitAll()
                auth.anyRequest().authenticated()
            }
            .oauth2Login { oauth2 ->
                oauth2.loginPage("/auth/login")
                oauth2.successHandler(oauth2LoginSuccessHandler)
                oauth2.failureHandler(oauth2LoginFailureHandler)
            }
            .authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()
    }

    @Bean
    fun authenticationProvider(
        localUserDetailsService: LocalUserDetailsService,
        passwordEncoder: PasswordEncoder,
    ): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider(localUserDetailsService)
        provider.setPasswordEncoder(passwordEncoder)
        return provider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager =
        authenticationConfiguration.authenticationManager
}
