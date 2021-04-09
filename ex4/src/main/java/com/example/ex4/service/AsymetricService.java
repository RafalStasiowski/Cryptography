package com.example.ex4.service;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.security.*;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;

@Service
public class AsymetricService {
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    @Getter
    private PrivateKey privateKey;
    @Getter
    private PublicKey publicKey;

    public void generateKeys() throws NoSuchAlgorithmException {
        int keyLength = 1024;
        keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(keyLength);
        pair = keyGen.generateKeyPair();
        System.out.println(pair.getPrivate().getFormat());
        System.out.println(pair.getPublic().getFormat());
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public static String encodeAsOpenSSH() {
        this.privateKey.
    }

}
