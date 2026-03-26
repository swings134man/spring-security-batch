package com.lucas.socialsample.models.auth.dto

import com.lucas.socialsample.models.user.entity.UserRole
import com.lucas.socialsample.models.user.entity.UserStatus

data class TokenPair(
    val accessToken: String,
    val refreshToken: String,
)

data class MeResult(
    val userId: Long,
    val email: String?,
    val nickname: String,
    val role: UserRole,
    val status: UserStatus,
)

data class SocialLoginResult(
    val needsAdditionalInfo: Boolean,
    val pendingToken: String? = null,
    val tokenPair: TokenPair? = null,
)
