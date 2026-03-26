package com.lucas.socialsample.models.social.dto

data class SocialUserInfo(
    val providerUserId: String,
    val email: String? = null,
    val name: String? = null,
    val profileImageUrl: String? = null,
)
