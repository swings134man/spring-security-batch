package com.lucas.socialsample.rest.dto

data class SignUpRequest(
    val email: String,
    val password: String,
    val nickname: String,
    val name: String? = null,
    val phoneNumber: String? = null,
)

data class LoginRequest(
    val loginId: String,
    val password: String,
)

data class RefreshRequest(
    val refreshToken: String,
)

data class LogoutRequest(
    val refreshToken: String,
)

data class CompleteSocialSignUpRequest(
    val pendingToken: String,
    val nickname: String,
    val phoneNumber: String,
    val agreedMarketing: Boolean = false,
)
