package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.infra.configs.JwtProperties
import com.lucas.socialsample.infra.security.JwtTokenProvider
import com.lucas.socialsample.models.user.entity.User
import com.lucas.socialsample.models.user.entity.UserStatus
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class JwtTokenProviderTest : FunSpec({
    val provider = JwtTokenProvider(
        JwtProperties(
            secret = "local-dev-secret-key-must-be-at-least-256-bits-long-for-hs256-algorithm",
            issuer = "social-sample-test",
            accessTokenMinutes = 60,
            refreshTokenDays = 7,
            pendingTokenMinutes = 30,
        ),
    )

    test("access token claim을 파싱할 수 있다") {
        val user = User(
            id = 1L,
            email = "tester@example.com",
            nickname = "tester",
            status = UserStatus.ACTIVE,
        )

        val access = provider.createAccessToken(user)
        val principal = provider.extractPrincipal(access)

        principal.userId shouldBe 1L
        principal.email shouldBe "tester@example.com"
        principal.nickname shouldBe "tester"
    }

    test("refresh token type을 검증한다") {
        val refresh = provider.createRefreshToken(7L)

        provider.extractSubjectIfType(refresh, "refresh") shouldBe 7L
    }
})
