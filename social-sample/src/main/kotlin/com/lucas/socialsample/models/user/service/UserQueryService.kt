package com.lucas.socialsample.models.user.service

import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.models.user.entity.User
import com.lucas.socialsample.models.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserQueryService(
    private val userRepository: UserRepository,
) {
    fun getById(userId: Long): User {
        return userRepository.findById(userId)
            .orElseThrow { DomainException("NOT_FOUND", "user not found") }
    }
}
