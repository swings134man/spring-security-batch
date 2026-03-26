package com.lucas.socialsample.rest.controller

import com.lucas.socialsample.models.auth.dto.CompleteSocialSignUpCommand
import com.lucas.socialsample.models.auth.dto.LoginCommand
import com.lucas.socialsample.models.auth.dto.SignUpCommand
import com.lucas.socialsample.models.auth.service.AuthService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class PageController(
    private val authService: AuthService,
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
        return "redirect:/auth/success?accessToken=${result.accessToken}&refreshToken=${result.refreshToken}"
    }

    @PostMapping("/auth/login")
    fun login(
        @RequestParam loginId: String,
        @RequestParam password: String,
    ): String {
        val result = authService.login(LoginCommand(loginId = loginId, password = password))
        return "redirect:/auth/success?accessToken=${result.accessToken}&refreshToken=${result.refreshToken}"
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
    ): String {
        val result = authService.completeSocialSignUp(
            CompleteSocialSignUpCommand(
                pendingToken = pendingToken,
                nickname = nickname,
                phoneNumber = phoneNumber,
                agreedMarketing = agreedMarketing,
            ),
        )
        return "redirect:/auth/success?accessToken=${result.accessToken}&refreshToken=${result.refreshToken}"
    }

    @GetMapping("/auth/success")
    fun successPage(
        @RequestParam accessToken: String,
        @RequestParam refreshToken: String,
        model: Model,
    ): String {
        model.addAttribute("accessToken", accessToken)
        model.addAttribute("refreshToken", refreshToken)
        return "auth/success"
    }

    @GetMapping("/auth/token-lab")
    fun tokenLabPage(): String = "auth/token-lab"
}
