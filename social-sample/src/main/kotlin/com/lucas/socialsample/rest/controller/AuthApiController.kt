package com.lucas.socialsample.rest.controller

import com.lucas.socialsample.infra.common.ApiResponse
import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.infra.security.UserPrincipal
import com.lucas.socialsample.models.auth.dto.CompleteSocialSignUpCommand
import com.lucas.socialsample.models.auth.dto.LoginCommand
import com.lucas.socialsample.models.auth.dto.SignUpCommand
import com.lucas.socialsample.models.auth.service.AuthService
import com.lucas.socialsample.rest.dto.CompleteSocialSignUpRequest
import com.lucas.socialsample.rest.dto.LoginRequest
import com.lucas.socialsample.rest.dto.LogoutRequest
import com.lucas.socialsample.rest.dto.RefreshRequest
import com.lucas.socialsample.rest.dto.SignUpRequest
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
) {

    @PostMapping("/signup")
    fun signUp(@RequestBody request: SignUpRequest) =
        ApiResponse.ok(
            authService.signUp(
                SignUpCommand(
                    email = request.email,
                    password = request.password,
                    nickname = request.nickname,
                    name = request.name,
                    phoneNumber = request.phoneNumber,
                ),
            ),
        )

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest) =
        ApiResponse.ok(
            authService.login(
                LoginCommand(
                    loginId = request.loginId,
                    password = request.password,
                ),
            ),
        )

    @PostMapping("/refresh")
    fun refresh(@RequestBody request: RefreshRequest) =
        ApiResponse.ok(authService.refresh(request.refreshToken))

    @PostMapping("/logout")
    fun logout(@RequestBody request: LogoutRequest): ApiResponse<String> {
        authService.logout(request.refreshToken)
        return ApiResponse.ok("ok")
    }

    @PostMapping("/social/complete")
    fun complete(@RequestBody request: CompleteSocialSignUpRequest) =
        ApiResponse.ok(
            authService.completeSocialSignUp(
                CompleteSocialSignUpCommand(
                    pendingToken = request.pendingToken,
                    nickname = request.nickname,
                    phoneNumber = request.phoneNumber,
                    agreedMarketing = request.agreedMarketing,
                ),
            ),
        )

    @GetMapping("/me")
    fun me(@AuthenticationPrincipal principal: UserPrincipal?): ApiResponse<Any> {
        val userPrincipal = principal ?: throw DomainException("UNAUTHORIZED", "authentication required")
        return ApiResponse.ok(authService.me(userPrincipal))
    }
}
