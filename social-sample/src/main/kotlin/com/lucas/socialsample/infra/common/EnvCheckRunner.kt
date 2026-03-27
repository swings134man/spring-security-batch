package com.lucas.socialsample.infra.common

import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

/**
 * EnvCheckRunner.kt: env variable check runner. 애플리케이션 실행 시점에 필요한 env variable이 잘 설정되어 있는지 로그로 확인하기 위한 클래스
 *
 * @author: lucaskang(swings134man)
 * @since: 2026. 3. 27. 오후 4:34
 * @description:
 */
@Component
class EnvCheckRunner : CommandLineRunner {
    val log = logger()
    override fun run(vararg args: String) {
        log.info("KAKAO_CLIENT_ID = ${System.getenv("KAKAO_CLIENT_ID")}")
        log.info("KAKAO_CLIENT_SECRET = ${System.getenv("KAKAO_CLIENT_SECRET")}")
    }
}