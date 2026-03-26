package com.lucas.socialsample.models.auth.dto

data class SignUpCommand(
    val email: String,
    val password: String,
    val nickname: String,
    val name: String? = null,
    val phoneNumber: String? = null,
)

data class LoginCommand(
    val loginId: String,
    val password: String,
)

data class CompleteSocialSignUpCommand(
    val pendingToken: String,
    val nickname: String,
    val phoneNumber: String,
    val agreedMarketing: Boolean = false,
)
