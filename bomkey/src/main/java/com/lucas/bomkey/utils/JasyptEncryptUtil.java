package com.lucas.bomkey.utils;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JasyptEncryptUtil {

    private static StringEncryptor stringEncryptor;

    @Autowired
    public void JasyptEncryptUtil(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public static String encrypt(String data) {
        return stringEncryptor.encrypt(data);
    }

    public static String decrypt(String data) {
        return stringEncryptor.decrypt(data);
    }
}
