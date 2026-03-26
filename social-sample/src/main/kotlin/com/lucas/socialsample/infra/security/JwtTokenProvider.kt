package com.lucas.socialsample.infra.security

import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.infra.configs.JwtProperties
import com.lucas.socialsample.models.user.entity.User
import com.lucas.socialsample.models.user.entity.UserRole
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.Date

@Component
class JwtTokenProvider(
    private val jwtProperties: JwtProperties,
) {
    private val key = Keys.hmacShaKeyFor(jwtProperties.secret.toByteArray(StandardCharsets.UTF_8))

    fun createAccessToken(user: User): String {
        val userId = user.id ?: throw DomainException("BAD_REQUEST", "user id is required")
        val now = Instant.now()
        val expiry = now.plus(jwtProperties.accessTokenMinutes, ChronoUnit.MINUTES)
        return Jwts.builder()
            .subject(userId.toString())
            .issuer(jwtProperties.issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiry))
            .claim("type", "access")
            .claim("email", user.email)
            .claim("nickname", user.nickname)
            .claim("role", user.role.name)
            .signWith(key)
            .compact()
    }

    fun createRefreshToken(userId: Long): String {
        val now = Instant.now()
        val expiry = now.plus(jwtProperties.refreshTokenDays, ChronoUnit.DAYS)
        return Jwts.builder()
            .subject(userId.toString())
            .issuer(jwtProperties.issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiry))
            .claim("type", "refresh")
            .signWith(key)
            .compact()
    }

    fun createPendingToken(userId: Long): String {
        val now = Instant.now()
        val expiry = now.plus(jwtProperties.pendingTokenMinutes, ChronoUnit.MINUTES)
        return Jwts.builder()
            .subject(userId.toString())
            .issuer(jwtProperties.issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(expiry))
            .claim("type", "pending")
            .signWith(key)
            .compact()
    }

    fun parseClaims(token: String): Claims {
        return try {
            Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .payload
        } catch (ex: Exception) {
            throw DomainException("INVALID_TOKEN", "invalid token")
        }
    }

    fun extractPrincipal(token: String): UserPrincipal {
        val claims = parseClaims(token)
        if (claims["type"] != "access") {
            throw DomainException("INVALID_TOKEN", "not access token")
        }

        val role = runCatching { UserRole.valueOf(claims["role"].toString()) }
            .getOrElse { throw DomainException("INVALID_TOKEN", "invalid role claim") }

        return UserPrincipal(
            userId = claims.subject.toLong(),
            email = claims["email"]?.toString(),
            nickname = claims["nickname"]?.toString() ?: "",
            role = role,
        )
    }

    fun extractSubjectIfType(token: String, type: String): Long {
        val claims = parseClaims(token)
        if (claims["type"] != type) {
            throw DomainException("INVALID_TOKEN", "invalid token type")
        }
        return claims.subject.toLong()
    }
}
