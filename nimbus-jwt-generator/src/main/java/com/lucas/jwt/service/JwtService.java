package com.lucas.jwt.service;

import com.lucas.jwt.provider.RsaKeyProvider;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

public class JwtService {
    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15분
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7일

    // Access Token 생성
    public static String createAccessToken(String userId, String role) throws Exception {
        return createToken(userId, role, ACCESS_TOKEN_EXPIRATION);
    }

    // Refresh Token 생성
    public static String createRefreshToken(String userId) throws Exception {
        return createToken(userId, null, REFRESH_TOKEN_EXPIRATION);
    }

    private static String createToken(String userId, String role, long expirationMillis) throws Exception {
        // RSA 키 가져오기
        RSAKey rsaKey = RsaKeyProvider.getRsaKey();

        // Claim 설정
        JWTClaimsSet.Builder claimsSetBuilder = new JWTClaimsSet.Builder()
                .subject(userId)
                .issuer("your-application")
                .claim("test", "test Value")
                .expirationTime(new Date(System.currentTimeMillis() + expirationMillis))
                .issueTime(new Date());

        if (role != null) {
            claimsSetBuilder.claim("role", role);
        }

        JWTClaimsSet claimsSet = claimsSetBuilder.build();

        // JWT 서명
        JWSSigner signer = new RSASSASigner(rsaKey);
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                claimsSet
        );
        // Sign
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }

    // For TEST
    @Deprecated
    private static final long ACCESS_TOKEN_EXPIRATION_TEST = -3000; // 3sec

    @Deprecated
    public static String createAccessTokenForTest(String userId, String role) throws Exception {
        return createToken(userId, role, ACCESS_TOKEN_EXPIRATION_TEST);
    }
}
