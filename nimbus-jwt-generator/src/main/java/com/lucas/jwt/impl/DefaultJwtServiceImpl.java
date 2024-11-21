package com.lucas.jwt.impl;

import com.lucas.jwt.interfaces.JwtServiceInterface;
import com.lucas.jwt.interfaces.RsaKeyProviderInterface;
import com.lucas.jwt.provider.RsaKeyProvider;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import java.util.Date;

// @Service ?
public class DefaultJwtServiceImpl implements JwtServiceInterface {

    private final RsaKeyProviderInterface rsaKeyProvider;

    public DefaultJwtServiceImpl(RsaKeyProviderInterface rsaKeyProvider) {
        this.rsaKeyProvider = rsaKeyProvider;
    }

    private static final long ACCESS_TOKEN_EXPIRATION = 15 * 60 * 1000; // 15 Minute
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7 Days

    @Override
    public String createAccessToken(String userId, String role) throws Exception {
        return createToken(userId, role, ACCESS_TOKEN_EXPIRATION);
    }

    @Override
    public String createRefreshToken(String userId) throws Exception {
        return createToken(userId, null, REFRESH_TOKEN_EXPIRATION);
    }

    private String createToken(String userId, String role, long expirationMillis) throws Exception {
        RSAKey rsaKey = rsaKeyProvider.getRsaKey();

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

        JWSSigner signer = new RSASSASigner(rsaKey);
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader.Builder(JWSAlgorithm.RS256).keyID(rsaKey.getKeyID()).build(),
                claimsSet
        );
        signedJWT.sign(signer);

        return signedJWT.serialize();
    }
}
