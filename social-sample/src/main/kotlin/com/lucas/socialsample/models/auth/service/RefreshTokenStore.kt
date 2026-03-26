package com.lucas.socialsample.models.auth.service

interface RefreshTokenStore {
    fun save(userId: Long, refreshToken: String)
    fun find(userId: Long): String?
    fun delete(userId: Long)
}
