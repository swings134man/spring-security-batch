package com.lucas.jwt.provider;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

public class RsaKeyProvider {

    private static RSAKey rsaKey;

    // RSAKey init
    public static void initializeKey() throws JOSEException {
        if(rsaKey == null){
            rsaKey = new RSAKeyGenerator(2048)
                    .keyID("lucas") // 고유 Key 식별 ID
                    .generate();
        }
    }

    public static RSAKey getRsaKey() throws JOSEException {
        if(rsaKey == null) initializeKey();
        return rsaKey;
    }

    /**
     * RSAKey Object return to JWK Format
     * This JWK Object include Public Key Information
     * @return
     * @throws JOSEException
     */
    public static RSAKey getPublicKey() throws JOSEException {
        return getRsaKey().toPublicJWK();
    }
}
