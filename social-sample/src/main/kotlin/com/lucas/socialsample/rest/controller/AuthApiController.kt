package com.lucas.socialsample.rest.controller

import com.lucas.socialsample.infra.common.ApiResponse
import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.infra.configs.JwtProperties
import com.lucas.socialsample.infra.security.UserPrincipal
import com.lucas.socialsample.models.auth.dto.CompleteSocialSignUpCommand
import com.lucas.socialsample.models.auth.dto.LoginCommand
import com.lucas.socialsample.models.auth.dto.SignUpCommand
import com.lucas.socialsample.models.auth.service.AuthService
import com.lucas.socialsample.rest.dto.CompleteSocialSignUpRequest
import com.lucas.socialsample.rest.dto.LoginRequest
import com.lucas.socialsample.rest.dto.SignUpRequest
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthApiController(
    private val authService: AuthService,
    private val jwtProperties: JwtProperties,
    @Value("\${app.auth-cookie.secure:true}") private val authCookieSecure: Boolean,
) {

    @PostMapping("/signup")
    fun signUp(
        @RequestBody request: SignUpRequest,
        response: HttpServletResponse,
    ): ApiResponse<Any> {
        val tokenPair = authService.signUp(
            SignUpCommand(
                email = request.email,
                password = request.password,
                nickname = request.nickname,
                name = request.name,
                phoneNumber = request.phoneNumber,
            ),
        )
        response.setHeader("X-Access-Token", tokenPair.accessToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${tokenPair.refreshToken}; Max-Age=${jwtProperties.refreshTokenDays * 24 * 60 * 60}; Path=/; HttpOnly; ${
                if (authCookieSecure) "Secure; " else ""
            }SameSite=Lax",
        )
        return ApiResponse.ok("ok")
    }

    @PostMapping("/login")
    fun login(
        @RequestBody request: LoginRequest,
        response: HttpServletResponse,
    ): ApiResponse<Any> {
        val tokenPair = authService.login(
            LoginCommand(
                loginId = request.loginId,
                password = request.password,
            ),
        )
        response.setHeader("X-Access-Token", tokenPair.accessToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${tokenPair.refreshToken}; Max-Age=${jwtProperties.refreshTokenDays * 24 * 60 * 60}; Path=/; HttpOnly; ${
                if (authCookieSecure) "Secure; " else ""
            }SameSite=Lax",
        )
        return ApiResponse.ok("ok")
    }

    @PostMapping("/refresh")
    fun refresh(
        httpRequest: HttpServletRequest,
        response: HttpServletResponse,
    ): ApiResponse<Any> {
        val refreshToken = httpRequest.cookies
            ?.firstOrNull { it.name == "refreshToken" }
            ?.value
            ?: throw DomainException("BAD_REQUEST", "refresh token is required")
        val tokenPair = authService.refresh(refreshToken)
        response.setHeader("X-Access-Token", tokenPair.accessToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${tokenPair.refreshToken}; Max-Age=${jwtProperties.refreshTokenDays * 24 * 60 * 60}; Path=/; HttpOnly; ${
                if (authCookieSecure) "Secure; " else ""
            }SameSite=Lax",
        )
        return ApiResponse.ok("ok")
    }

    @PostMapping("/logout")
    fun logout(
        httpRequest: HttpServletRequest,
        response: HttpServletResponse,
    ): ApiResponse<String> {
        val refreshToken = httpRequest.cookies
            ?.firstOrNull { it.name == "refreshToken" }
            ?.value
            ?: throw DomainException("BAD_REQUEST", "refresh token is required")
        authService.logout(refreshToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=; Max-Age=0; Path=/; HttpOnly; ${if (authCookieSecure) "Secure; " else ""}SameSite=Lax",
        )
        return ApiResponse.ok("ok")
    }

    @PostMapping("/social/complete")
    fun complete(
        @RequestBody request: CompleteSocialSignUpRequest,
        response: HttpServletResponse,
    ): ApiResponse<Any> {
        val tokenPair = authService.completeSocialSignUp(
            CompleteSocialSignUpCommand(
                pendingToken = request.pendingToken,
                nickname = request.nickname,
                phoneNumber = request.phoneNumber,
                agreedMarketing = request.agreedMarketing,
            ),
        )
        response.setHeader("X-Access-Token", tokenPair.accessToken)
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${tokenPair.refreshToken}; Max-Age=${jwtProperties.refreshTokenDays * 24 * 60 * 60}; Path=/; HttpOnly; ${
                if (authCookieSecure) "Secure; " else ""
            }SameSite=Lax",
        )
        return ApiResponse.ok("ok")
    }

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal principal: UserPrincipal?): ApiResponse<Any> {
        val userPrincipal = principal ?: throw DomainException("UNAUTHORIZED", "authentication required")
        return ApiResponse.ok(authService.me(userPrincipal))
    }
}
