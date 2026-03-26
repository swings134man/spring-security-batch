package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.infra.configs.JwtProperties
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class RedisRefreshTokenStore(
    private val redisTemplate: StringRedisTemplate,
    private val jwtProperties: JwtProperties,
) : RefreshTokenStore {

    override fun save(userId: Long, refreshToken: String) {
        redisTemplate.opsForValue().set(
            key(userId),
            refreshToken,
            Duration.ofDays(jwtProperties.refreshTokenDays),
        )
    }

    override fun find(userId: Long): String? = redisTemplate.opsForValue().get(key(userId))

    override fun delete(userId: Long) {
        redisTemplate.delete(key(userId))
    }

    private fun key(userId: Long): String = "refresh-token:$userId"
}
