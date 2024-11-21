package com.lucas.jwt.interfaces;

import com.lucas.jwt.obj.ValidationResult;
import com.nimbusds.jwt.SignedJWT;

import java.text.ParseException;

public interface JwtValidatorInterface {

    ValidationResult validateToken(String token);

    boolean isTokenExpired(SignedJWT signedJWT) throws ParseException;

    String getClaim(String token, String claimName) throws ParseException;
}
