package com.lucas.jwt.interfaces;

public interface JwtServiceInterface {
    String createAccessToken(String userId, String role) throws Exception;
    String createRefreshToken(String userId) throws Exception;
}
