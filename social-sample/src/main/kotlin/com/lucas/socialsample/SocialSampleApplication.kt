package com.lucas.socialsample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class])
@EnableJpaAuditing
class SocialSampleApplication

fun main(args: Array<String>) {
    runApplication<SocialSampleApplication>(*args)
}
