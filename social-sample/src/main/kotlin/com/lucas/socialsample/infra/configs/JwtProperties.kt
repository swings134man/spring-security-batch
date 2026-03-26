package com.lucas.socialsample.infra.configs

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "app.jwt")
data class JwtProperties(
    val secret: String,
    val issuer: String = "social-sample",
    val accessTokenMinutes: Long = 60,
    val refreshTokenDays: Long = 7,
    val pendingTokenMinutes: Long = 30,
)
