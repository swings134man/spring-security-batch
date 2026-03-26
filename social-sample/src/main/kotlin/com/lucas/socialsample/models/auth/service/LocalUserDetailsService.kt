package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.models.user.repository.AuthAccountRepository
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class LocalUserDetailsService(
    private val authAccountRepository: AuthAccountRepository,
) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val account = authAccountRepository.findByLoginId(username)
            ?: throw UsernameNotFoundException("user not found")

        val password = account.passwordHash
            ?: throw DomainException("UNAUTHORIZED", "password account not found")
        val role = account.user.role.name
        val loginId = account.loginId ?: throw DomainException("UNAUTHORIZED", "login id not found")

        return User(
            loginId,
            password,
            listOf(SimpleGrantedAuthority("ROLE_$role")),
        )
    }
}
