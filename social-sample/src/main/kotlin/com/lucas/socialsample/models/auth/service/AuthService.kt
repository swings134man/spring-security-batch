package com.lucas.socialsample.models.auth.service

import com.lucas.socialsample.infra.common.exception.DomainException
import com.lucas.socialsample.infra.security.JwtTokenProvider
import com.lucas.socialsample.infra.security.UserPrincipal
import com.lucas.socialsample.models.auth.dto.CompleteSocialSignUpCommand
import com.lucas.socialsample.models.auth.dto.LoginCommand
import com.lucas.socialsample.models.auth.dto.MeResult
import com.lucas.socialsample.models.auth.dto.SignUpCommand
import com.lucas.socialsample.models.auth.dto.SocialLoginResult
import com.lucas.socialsample.models.auth.dto.TokenPair
import com.lucas.socialsample.models.auth.entity.AuthAccount
import com.lucas.socialsample.models.auth.entity.AuthType
import com.lucas.socialsample.models.social.dto.SocialUserInfo
import com.lucas.socialsample.models.user.entity.User
import com.lucas.socialsample.models.user.entity.UserStatus
import com.lucas.socialsample.models.user.repository.AuthAccountRepository
import com.lucas.socialsample.models.user.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val authAccountRepository: AuthAccountRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenProvider: JwtTokenProvider,
    private val refreshTokenStore: RefreshTokenStore,
) {

    @Transactional
    fun signUp(command: SignUpCommand): TokenPair {
        if (authAccountRepository.existsByLoginId(command.email)) {
            throw DomainException("BAD_REQUEST", "login id already exists")
        }

        val user = userRepository.save(
            User(
                email = command.email,
                nickname = command.nickname,
                name = command.name,
                phoneNumber = command.phoneNumber,
                status = UserStatus.ACTIVE,
            ),
        )

        authAccountRepository.save(
            AuthAccount(
                user = user,
                authType = AuthType.LOCAL,
                loginId = command.email,
                passwordHash = passwordEncoder.encode(command.password),
            ),
        )

        user.updateLastLogin()
        return issueTokenPair(user)
    }

    @Transactional
    fun login(command: LoginCommand): TokenPair {
        val account = authAccountRepository.findByLoginId(command.loginId)
            ?: throw DomainException("UNAUTHORIZED", "invalid credentials")
        val passwordHash = account.passwordHash ?: throw DomainException("UNAUTHORIZED", "invalid credentials")
        if (!passwordEncoder.matches(command.password, passwordHash)) {
            throw DomainException("UNAUTHORIZED", "invalid credentials")
        }
        val user = account.user
        validateUserStatus(user)
        user.updateLastLogin()
        return issueTokenPair(user)
    }

    @Transactional
    fun processSocialLogin(authType: AuthType, socialUserInfo: SocialUserInfo): SocialLoginResult {
        val authAccount = authAccountRepository.findByAuthTypeAndProviderUserId(authType, socialUserInfo.providerUserId)
            ?: createPendingSocialAccount(authType, socialUserInfo)

        val user = authAccount.user
        if (user.status == UserStatus.PENDING) {
            return SocialLoginResult(
                needsAdditionalInfo = true,
                pendingToken = jwtTokenProvider.createPendingToken(user.id!!),
            )
        }

        validateUserStatus(user)
        user.updateLastLogin()
        return SocialLoginResult(
            needsAdditionalInfo = false,
            tokenPair = issueTokenPair(user),
        )
    }

    @Transactional
    fun completeSocialSignUp(command: CompleteSocialSignUpCommand): TokenPair {
        val userId = jwtTokenProvider.extractSubjectIfType(command.pendingToken, "pending")
        val user = userRepository.findById(userId)
            .orElseThrow { DomainException("NOT_FOUND", "user not found") }

        if (user.status != UserStatus.PENDING) {
            throw DomainException("BAD_REQUEST", "user is not pending")
        }

        user.nickname = command.nickname
        user.phoneNumber = command.phoneNumber
        user.activate()
        user.updateLastLogin()
        return issueTokenPair(user)
    }

    fun refresh(refreshToken: String): TokenPair {
        val userId = jwtTokenProvider.extractSubjectIfType(refreshToken, "refresh")
        val saved = refreshTokenStore.find(userId) ?: throw DomainException("TOKEN_EXPIRED", "refresh token expired")
        if (saved != refreshToken) {
            throw DomainException("INVALID_TOKEN", "refresh token mismatch")
        }

        val user = userRepository.findById(userId)
            .orElseThrow { DomainException("NOT_FOUND", "user not found") }
        validateUserStatus(user)
        return issueTokenPair(user)
    }

    fun logout(refreshToken: String) {
        val userId = jwtTokenProvider.extractSubjectIfType(refreshToken, "refresh")
        refreshTokenStore.delete(userId)
    }

    fun me(principal: UserPrincipal): MeResult {
        val user = userRepository.findById(principal.userId)
            .orElseThrow { DomainException("NOT_FOUND", "user not found") }
        return MeResult(
            userId = user.id!!,
            email = user.email,
            nickname = user.nickname,
            role = user.role,
            status = user.status,
        )
    }

    private fun createPendingSocialAccount(authType: AuthType, socialUserInfo: SocialUserInfo): AuthAccount {
        val user = userRepository.save(
            User(
                email = socialUserInfo.email,
                nickname = socialUserInfo.name?.takeIf { it.isNotBlank() } ?: "pending-${socialUserInfo.providerUserId.take(8)}",
                name = socialUserInfo.name,
                profileImageUrl = socialUserInfo.profileImageUrl,
                status = UserStatus.PENDING,
            ),
        )

        return authAccountRepository.save(
            AuthAccount(
                user = user,
                authType = authType,
                providerUserId = socialUserInfo.providerUserId,
            ),
        )
    }

    private fun issueTokenPair(user: User): TokenPair {
        val userId = user.id ?: throw DomainException("BAD_REQUEST", "user id missing")
        val accessToken = jwtTokenProvider.createAccessToken(user)
        val refreshToken = jwtTokenProvider.createRefreshToken(userId)
        refreshTokenStore.save(userId, refreshToken)
        return TokenPair(accessToken, refreshToken)
    }

    private fun validateUserStatus(user: User) {
        when (user.status) {
            UserStatus.ACTIVE -> return
            UserStatus.PENDING -> throw DomainException("FORBIDDEN", "sign up completion required")
            UserStatus.BLOCKED -> throw DomainException("FORBIDDEN", "blocked user")
            UserStatus.WITHDRAWN -> throw DomainException("FORBIDDEN", "withdrawn user")
        }
    }
}
