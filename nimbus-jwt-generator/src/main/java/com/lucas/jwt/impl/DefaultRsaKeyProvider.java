package com.lucas.jwt.impl;

import com.lucas.jwt.interfaces.RsaKeyProviderInterface;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.gen.RSAKeyGenerator;

public class DefaultRsaKeyProvider implements RsaKeyProviderInterface {
    private static RSAKey rsaKey;

    @Override
    public void initializeKey() throws JOSEException {
        if(rsaKey == null){
            rsaKey = new RSAKeyGenerator(2048)
                    .keyID("lucas") // 고유 Key 식별 ID
                    .generate();
        }
    }

    @Override
    public RSAKey getRsaKey() throws JOSEException {
        if(rsaKey == null) initializeKey();
        return rsaKey;
    }

    @Override
    public RSAKey getPublicKey() throws JOSEException {
        return getRsaKey().toPublicJWK();
    }
}
