package com.lucas.jwt.impl;

import com.lucas.jwt.interfaces.JwtValidatorInterface;
import com.lucas.jwt.obj.ValidationResult;
import com.lucas.jwt.provider.RsaKeyProvider;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

public class DefaultJwtValidator implements JwtValidatorInterface {
    @Override
    public ValidationResult validateToken(String token) {
        try {
            // JWT Parsing
            SignedJWT signedJWT = SignedJWT.parse(token);

            // Get Public RSA Key
            RSAKey publicKey = RsaKeyProvider.getPublicKey();
            RSASSAVerifier verifier = new RSASSAVerifier(publicKey.toRSAPublicKey());

            // 서명 검증
            if (!signedJWT.verify(verifier)) {
                return ValidationResult.failure("Invalid Signature");
            }

            // Check Jwt Token Expired
            if (isTokenExpired(signedJWT)) {
                return ValidationResult.failure("Token Expired");
            }

            return ValidationResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ValidationResult.failure("Unknown Validation Error");
        }
    }

    @Override
    public boolean isTokenExpired(SignedJWT signedJWT) throws ParseException {
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expirationTime == null) {
            return true; // If there is no expiration time, the token is considered invalid.
        }
        return new Date().after(expirationTime); // 현재 시간이 만료 시간을 초과했는지 확인
    }

    @Override
    public String getClaim(String token, String claimName) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getStringClaim(claimName);
    }
}
