package com.lucas.bomkey.test;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import org.junit.jupiter.api.Test;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

// JWK Key Test
class JwkSetTest {

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        }
        catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Test
    void jwkSetTest1() {
        // 1. Gen RSA Key
        KeyPair keyPair = generateRsaKey();
        System.out.println("pub Key" + keyPair.getPublic());
        System.out.println("private Key" + keyPair.getPrivate());

        // 2. Set RSAKey
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) keyPair.getPublic())
                .privateKey((RSAPrivateKey) keyPair.getPrivate())
                .keyID("test")
                .build();
        System.out.println("rsaKey = " + rsaKey);

        // 3. Set JWKSet
        JWKSet jwkSet = new JWKSet(rsaKey);
        ImmutableJWKSet<SecurityContext> securityContextImmutableJWKSet = new ImmutableJWKSet<>(jwkSet);
        System.out.println("jwkSet = " + jwkSet);
        System.out.println("securityContextImmutableJWKSet = " + securityContextImmutableJWKSet);
    }
}
