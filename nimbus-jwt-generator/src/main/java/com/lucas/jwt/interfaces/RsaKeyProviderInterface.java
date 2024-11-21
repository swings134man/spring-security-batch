package com.lucas.jwt.interfaces;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;

/*
    Using With @Configuration
 */
public interface RsaKeyProviderInterface {
    /**
     * RSAKey Init
     */
    void initializeKey() throws JOSEException;

    RSAKey getRsaKey() throws JOSEException;

    /**
     * RSAKey Object return to JWK Format
     * This JWK Object include Public Key Information
     */
    RSAKey getPublicKey() throws JOSEException;
}
