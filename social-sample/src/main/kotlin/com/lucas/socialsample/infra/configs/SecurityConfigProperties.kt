package com.lucas.socialsample.infra.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.security")
data class SecurityConfigProperties(
    val publicPaths: List<String> = listOf(
        "/",
        "/error",
        "/css/**",
        "/js/**",
        "/images/**",
        "/h2-console/**",
        "/auth/**",
        "/oauth2/**",
        "/login/**",
        "/social/complete/**"
    )
)
