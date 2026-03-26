package com.lucas.socialsample.infra.common

import org.slf4j.LoggerFactory
import kotlin.jvm.java

/**
 * CommonLogger.kt: 공통 로거
 *
 * @author: lucaskang(swings134man)
 * @since: 2025. 10. 2. 오전 1:08
 * @description: val logger = logger()
 */
inline fun <reified T> T.logger() = LoggerFactory.getLogger(T::class.java)!!