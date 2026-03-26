package com.lucas.socialsample.infra.common.exception

class DomainException(
    val code: String,
    override val message: String,
) : RuntimeException(message)
