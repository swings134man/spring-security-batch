package com.lucas.socialsample.infra.common.exception

import com.lucas.socialsample.infra.common.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(DomainException::class)
    fun handleDomainException(ex: DomainException): ResponseEntity<ApiResponse<Nothing>> {
        val status = when (ex.code) {
            "UNAUTHORIZED", "INVALID_TOKEN", "TOKEN_EXPIRED" -> HttpStatus.UNAUTHORIZED
            "FORBIDDEN" -> HttpStatus.FORBIDDEN
            "NOT_FOUND" -> HttpStatus.NOT_FOUND
            else -> HttpStatus.BAD_REQUEST
        }
        return ResponseEntity.status(status).body(ApiResponse.fail(ex.code, ex.message))
    }

    @ExceptionHandler(Exception::class)
    fun handleUnknown(ex: Exception): ResponseEntity<ApiResponse<Nothing>> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body(ApiResponse.fail("INTERNAL_ERROR", ex.message ?: "internal error"))
    }
}
