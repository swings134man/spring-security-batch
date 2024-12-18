package com.lucas.jwt.service;

import com.lucas.jwt.obj.ValidationResult;
import com.lucas.jwt.provider.RsaKeyProvider;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;
import java.util.Date;

@Deprecated
public class JwtValidator {
    public static ValidationResult validateToken(String token) {
        try {
            // JWT 파싱
            SignedJWT signedJWT = SignedJWT.parse(token);

            // RSA 공개 키 가져오기
            RSAKey publicKey = RsaKeyProvider.getPublicKey();
            RSASSAVerifier verifier = new RSASSAVerifier(publicKey.toRSAPublicKey());

            // 서명 검증
            if (!signedJWT.verify(verifier)) {
                return ValidationResult.failure("Invalid Signature");
            }

            // 토큰 만료 여부 확인
            if (isTokenExpired(signedJWT)) {
                return ValidationResult.failure("Token Expired");
            }

            return ValidationResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return ValidationResult.failure("Unknown Validation Error");
        }
    }

    private static boolean isTokenExpired(SignedJWT signedJWT) throws ParseException {
        Date expirationTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        if (expirationTime == null) {
            return true; // 만료 시간이 없으면 토큰을 유효하지 않다고 간주
        }
        return new Date().after(expirationTime); // 현재 시간이 만료 시간을 초과했는지 확인
    }

    public static String getClaim(String token, String claimName) throws ParseException {
        SignedJWT signedJWT = SignedJWT.parse(token);
        return signedJWT.getJWTClaimsSet().getStringClaim(claimName);
    }
}
