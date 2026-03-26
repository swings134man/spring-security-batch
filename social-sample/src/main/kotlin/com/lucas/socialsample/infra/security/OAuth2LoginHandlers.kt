package com.lucas.socialsample.infra.security

import com.lucas.socialsample.infra.configs.JwtProperties
import com.lucas.socialsample.models.auth.entity.AuthType
import com.lucas.socialsample.models.auth.service.AuthService
import com.lucas.socialsample.models.auth.service.SocialAttributeExtractor
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Component
class OAuth2LoginSuccessHandler(
    private val authService: AuthService,
    private val socialAttributeExtractor: SocialAttributeExtractor,
    private val jwtProperties: JwtProperties,
    @Value("\${app.auth-cookie.secure:true}") private val authCookieSecure: Boolean,
) : AuthenticationSuccessHandler {

    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication,
    ) {
        val oauthToken = authentication as OAuth2AuthenticationToken
        val registrationId = oauthToken.authorizedClientRegistrationId
        val authType = when (registrationId.lowercase()) {
            "kakao" -> AuthType.KAKAO
            "naver" -> AuthType.NAVER
            else -> throw IllegalArgumentException("unsupported oauth registration: $registrationId")
        }

        val attributes = oauthToken.principal?.attributes
            ?: throw IllegalArgumentException("oauth principal is missing")
        val socialUserInfo = socialAttributeExtractor.extract(authType, attributes)
        val result = authService.processSocialLogin(authType, socialUserInfo)

        if (result.needsAdditionalInfo) {
            val token = URLEncoder.encode(result.pendingToken, StandardCharsets.UTF_8)
            response.sendRedirect("/social/complete?pendingToken=$token")
            return
        }

        val tokenPair = result.tokenPair ?: throw IllegalStateException("token pair missing")
        response.setHeader("X-Access-Token", tokenPair.accessToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${tokenPair.refreshToken}; Max-Age=${jwtProperties.refreshTokenDays * 24 * 60 * 60}; Path=/; HttpOnly; ${
                if (authCookieSecure) "Secure; " else ""
            }SameSite=Lax",
        )
        val accessToken = URLEncoder.encode(tokenPair.accessToken, StandardCharsets.UTF_8)
        response.sendRedirect("/auth/success?accessToken=$accessToken")
    }
}

@Component
class OAuth2LoginFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: org.springframework.security.core.AuthenticationException,
    ) {
        val message = URLEncoder.encode(exception.message ?: "oauth login failed", StandardCharsets.UTF_8)
        response.sendRedirect("/auth/login?error=$message")
    }
}
