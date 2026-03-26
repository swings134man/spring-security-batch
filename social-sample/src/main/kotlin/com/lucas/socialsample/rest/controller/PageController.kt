package com.lucas.socialsample.rest.controller

import com.lucas.socialsample.models.auth.dto.CompleteSocialSignUpCommand
import com.lucas.socialsample.models.auth.dto.LoginCommand
import com.lucas.socialsample.models.auth.dto.SignUpCommand
import com.lucas.socialsample.models.auth.service.AuthService
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PageController(
    private val authService: AuthService,
    @Value("\${app.auth-cookie.secure:true}") private val authCookieSecure: Boolean,
) {

    @GetMapping("/")
    fun home(model: Model): String {
        model.addAttribute("title", "social-sample")
        return "index"
    }

    @GetMapping("/auth/login")
    fun loginPage(
        @RequestParam(required = false) error: String?,
        model: Model,
    ): String {
        model.addAttribute("error", error)
        return "auth/login"
    }

    @GetMapping("/auth/signup")
    fun signUpPage(): String = "auth/signup"

    @PostMapping("/auth/signup")
    fun signUp(
        @RequestParam email: String,
        @RequestParam password: String,
        @RequestParam nickname: String,
        @RequestParam(required = false) name: String?,
        @RequestParam(required = false) phoneNumber: String?,
        response: HttpServletResponse,
    ): String {
        val result = authService.signUp(
            SignUpCommand(
                email = email,
                password = password,
                nickname = nickname,
                name = name,
                phoneNumber = phoneNumber,
            ),
        )
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${result.refreshToken}; Max-Age=604800; Path=/; HttpOnly; ${if (authCookieSecure) "Secure; " else ""}SameSite=Lax",
        )
        return "redirect:/auth/success?accessToken=${result.accessToken}"
    }

    @PostMapping("/auth/login")
    fun login(
        @RequestParam loginId: String,
        @RequestParam password: String,
        response: HttpServletResponse,
    ): String {
        val result = authService.login(LoginCommand(loginId = loginId, password = password))
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${result.refreshToken}; Max-Age=604800; Path=/; HttpOnly; ${if (authCookieSecure) "Secure; " else ""}SameSite=Lax",
        )
        return "redirect:/auth/success?accessToken=${result.accessToken}"
    }

    @GetMapping("/social/complete")
    fun completePage(
        @RequestParam pendingToken: String,
        model: Model,
    ): String {
        model.addAttribute("pendingToken", pendingToken)
        return "social/complete"
    }

    @PostMapping("/social/complete")
    fun complete(
        @RequestParam pendingToken: String,
        @RequestParam nickname: String,
        @RequestParam phoneNumber: String,
        @RequestParam(defaultValue = "false") agreedMarketing: Boolean,
        response: HttpServletResponse,
    ): String {
        val result = authService.completeSocialSignUp(
            CompleteSocialSignUpCommand(
                pendingToken = pendingToken,
                nickname = nickname,
                phoneNumber = phoneNumber,
                agreedMarketing = agreedMarketing,
            ),
        )
        response.addHeader(
            "Set-Cookie",
            "refreshToken=${result.refreshToken}; Max-Age=604800; Path=/; HttpOnly; ${if (authCookieSecure) "Secure; " else ""}SameSite=Lax",
        )
        return "redirect:/auth/success?accessToken=${result.accessToken}"
    }

    @GetMapping("/auth/success")
    fun successPage(
        @RequestParam accessToken: String,
        model: Model,
    ): String {
        model.addAttribute("accessToken", accessToken)
        return "auth/success"
    }

    @GetMapping("/auth/token-lab")
    fun tokenLabPage(): String = "auth/token-lab"
}
