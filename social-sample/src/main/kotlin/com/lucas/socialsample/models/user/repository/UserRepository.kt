package com.lucas.socialsample.models.user.repository

import com.lucas.socialsample.models.user.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): User?
}
