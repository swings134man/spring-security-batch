package com.lucas.socialsample.infra.security

import com.lucas.socialsample.models.user.entity.UserRole

data class UserPrincipal(
    val userId: Long,
    val email: String?,
    val nickname: String,
    val role: UserRole,
)
