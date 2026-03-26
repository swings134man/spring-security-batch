package com.lucas.socialsample.models.user.entity

import com.lucas.socialsample.infra.common.Auditable
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email", length = 255)
    var email: String? = null,

    @Column(name = "nickname", nullable = false, length = 100)
    var nickname: String,

    @Column(name = "name", length = 100)
    var name: String? = null,

    @Column(name = "phone_number", length = 30)
    var phoneNumber: String? = null,

    @Column(name = "profile_image_url", length = 500)
    var profileImageUrl: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    var status: UserStatus = UserStatus.PENDING,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false, length = 20)
    var role: UserRole = UserRole.USER,

    @Column(name = "last_login_at")
    var lastLoginAt: LocalDateTime? = null,
) : Auditable() {
    fun activate() {
        status = UserStatus.ACTIVE
    }

    fun updateLastLogin() {
        lastLoginAt = LocalDateTime.now()
    }
}
