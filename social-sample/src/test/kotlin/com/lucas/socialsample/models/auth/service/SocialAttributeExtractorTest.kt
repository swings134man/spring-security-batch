package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.models.auth.entity.AuthType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SocialAttributeExtractorTest : FunSpec({
    val extractor = SocialAttributeExtractor()

    test("kakao attributes를 표준 정보로 변환한다") {
        val attributes = mapOf(
            "id" to 1001,
            "kakao_account" to mapOf(
                "email" to "kakao@example.com",
                "profile" to mapOf(
                    "nickname" to "kakao-user",
                    "profile_image_url" to "https://image.example.com/kakao.png",
                ),
            ),
        )

        val result = extractor.extract(AuthType.KAKAO, attributes)

        result.providerUserId shouldBe "1001"
        result.email shouldBe "kakao@example.com"
        result.name shouldBe "kakao-user"
    }

    test("naver attributes를 표준 정보로 변환한다") {
        val attributes = mapOf(
            "response" to mapOf(
                "id" to "N-1",
                "email" to "naver@example.com",
                "name" to "naver-user",
                "profile_image" to "https://image.example.com/naver.png",
            ),
        )

        val result = extractor.extract(AuthType.NAVER, attributes)

        result.providerUserId shouldBe "N-1"
        result.email shouldBe "naver@example.com"
        result.name shouldBe "naver-user"
    }
})
