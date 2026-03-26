package com.lucas.socialsample.models.user.repository

import com.lucas.socialsample.models.auth.entity.AuthAccount
import com.lucas.socialsample.models.auth.entity.AuthType
import org.springframework.data.jpa.repository.JpaRepository

interface AuthAccountRepository : JpaRepository<AuthAccount, Long> {
    fun existsByLoginId(loginId: String): Boolean

    fun findByLoginId(loginId: String): AuthAccount?

    fun findByAuthTypeAndProviderUserId(authType: AuthType, providerUserId: String): AuthAccount?
}
