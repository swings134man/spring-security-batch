package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.models.auth.entity.AuthType
import com.lucas.socialsample.models.social.dto.SocialUserInfo
import org.springframework.stereotype.Component

@Component
class SocialAttributeExtractor {

    fun extract(authType: AuthType, attributes: Map<String, Any>): SocialUserInfo {
        return when (authType) {
            AuthType.KAKAO -> extractKakao(attributes)
            AuthType.NAVER -> extractNaver(attributes)
            else -> throw IllegalArgumentException("unsupported auth type: $authType")
        }
    }

    private fun extractKakao(attributes: Map<String, Any>): SocialUserInfo {
        val id = attributes["id"]?.toString() ?: throw IllegalArgumentException("kakao id missing")
        val account = attributes["kakao_account"] as? Map<*, *>
        val profile = account?.get("profile") as? Map<*, *>

        return SocialUserInfo(
            providerUserId = id,
            email = account?.get("email")?.toString(),
            name = profile?.get("nickname")?.toString(),
            profileImageUrl = profile?.get("profile_image_url")?.toString(),
        )
    }

    private fun extractNaver(attributes: Map<String, Any>): SocialUserInfo {
        val response = attributes["response"] as? Map<*, *>
            ?: throw IllegalArgumentException("naver response missing")
        val id = response["id"]?.toString() ?: throw IllegalArgumentException("naver id missing")

        return SocialUserInfo(
            providerUserId = id,
            email = response["email"]?.toString(),
            name = response["name"]?.toString() ?: response["nickname"]?.toString(),
            profileImageUrl = response["profile_image"]?.toString(),
        )
    }
}
