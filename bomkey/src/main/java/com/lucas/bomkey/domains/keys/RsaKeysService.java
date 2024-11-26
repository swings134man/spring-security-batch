package com.lucas.bomkey.domains.keys;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RsaKeysService {

    private final RsaKeysRepository repository;

    public RsaKeys saveRsaKeys(String identifier) {
        RsaKeys rsaKeys = new RsaKeys();

        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        rsaKeys.setIdentifier(identifier);
        rsaKeys.setKeyId(UUID.randomUUID().toString());
        rsaKeys.setPublicKey(Base64.getEncoder().encodeToString(publicKey.getEncoded()));
        rsaKeys.setPrivateKey(Base64.getEncoder().encodeToString(privateKey.getEncoded()));

        return repository.save(rsaKeys);
    }

    public ImmutableJWKSet<SecurityContext> loadByJwkSet(String identifier) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var result = repository.findByIdentifier(identifier)
                .orElseThrow(() -> new IllegalArgumentException("Not found RSA Keys"));

        RSAPublicKey publicKey = (RSAPublicKey) getKeyFromEncodedString(result.getPublicKey(), "RSA", true);
        RSAPrivateKey privateKey = (RSAPrivateKey) getKeyFromEncodedString(result.getPrivateKey(), "RSA", false);
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(result.getKeyId())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }


    private Key getKeyFromEncodedString(String encodedKey, String algorithm, boolean isPublic) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
        KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
        if (isPublic) {
            return keyFactory.generatePublic(new X509EncodedKeySpec(decodedKey));
        } else {
            return keyFactory.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
        }
    }

    /**
     * Gen RSA Key
     * @return
     */
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
}
