package com.lucas.socialsample.models.auth.entity

import com.lucas.socialsample.infra.common.Auditable
import com.lucas.socialsample.models.user.entity.User
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

@Entity
@Table(name = "auth_accounts")
class AuthAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    val user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "auth_type", nullable = false, length = 20)
    val authType: AuthType,

    @Column(name = "provider_user_id", length = 255)
    val providerUserId: String? = null,

    @Column(name = "login_id", length = 255)
    val loginId: String? = null,

    @Column(name = "password_hash", length = 255)
    var passwordHash: String? = null,

    @Column(name = "is_primary", nullable = false)
    val isPrimary: Boolean = true,
) : Auditable()
