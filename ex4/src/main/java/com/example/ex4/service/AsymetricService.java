package com.example.ex4.service;

import com.example.ex4.model.AsymetricKey;
import lombok.Getter;
import org.springframework.stereotype.Service;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

@Service
public class AsymetricService {
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    @Getter
    private PrivateKey privateKey;
    @Getter
    private PublicKey publicKey;


    /**
     * Generates private and public key then set them
     * @throws NoSuchAlgorithmException
     */
    public void generateKeys() throws NoSuchAlgorithmException {
        int keyLength = 1024;
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keyLength);
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = this.pair.getPrivate();
        this.publicKey = this.pair.getPublic();
    }

    /**
     * Function that set keys to value of AsymetricKey object
     * @param asymetricKey takes AsymetricKey object as param then set private and public key.
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public void setKeys(AsymetricKey asymetricKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        X509EncodedKeySpec keySpecPub = new X509EncodedKeySpec(asymetricKey.getPublicKey().getBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpecPub);

        KeyFactory factory = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec keySpecPriv = new PKCS8EncodedKeySpec(asymetricKey.getPrivateKey().getBytes());
        RSAPrivateKey privateKey = (RSAPrivateKey) factory.generatePrivate(keySpecPriv);

        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    /**
     * Function that sign message and return it signature
     * @param messaage String value that is passed through api. Message then will be signed with private key
     * @param privateKey Private key that sign message.
     * @return Signature of message
     * @throws Exception
     */
    public String sign(String messaage, PrivateKey privateKey) throws Exception {
        Signature privateSignature = Signature.getInstance("SHA256withRSA");
        privateSignature.initSign(privateKey);
        privateSignature.update(messaage.getBytes(StandardCharsets.UTF_8));

        byte[] signature = privateSignature.sign();

        return Base64.getEncoder().encodeToString(signature);
    }

    /**
     * Function that verifies that message was signed and return boolean value
     * @param messaage String value that will be verified
     * @param signature String value that is signature created during signing process.
     * @param publicKey Public key
     * @return
     * @throws Exception
     */
    public boolean verify(String messaage, String signature, PublicKey publicKey) throws Exception {
        Signature publicSignature = Signature.getInstance("SHA256withRSA");
        publicSignature.initVerify(publicKey);
        publicSignature.update(messaage.getBytes(StandardCharsets.UTF_8));

        byte[] signatureBytes = Base64.getDecoder().decode(signature);

        return publicSignature.verify(signatureBytes);
    }

    /**
     * Funcion that encrypt message
     * @param messaage String value that will be encrypted
     * @param publicKey Public key
     * @return Encrypted String value
     * @throws Exception
     */
    public String encrypt(String messaage, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);

        byte[] cipherText = encryptCipher.doFinal(messaage.getBytes(StandardCharsets.UTF_8));

        return Base64.getEncoder().encodeToString(cipherText);
    }

    /**
     * Function that decrypt encrypted message
     * @param messaage String value with encrypted message
     * @param privateKey Private key
     * @return Decrypted String value
     * @throws Exception
     */
    public String decrypt(String messaage, PrivateKey privateKey) throws Exception {
        byte[] bytes = Base64.getDecoder().decode(messaage);

        Cipher decriptCipher = Cipher.getInstance("RSA");
        decriptCipher.init(Cipher.DECRYPT_MODE, privateKey);

        return new String(decriptCipher.doFinal(bytes), "UTF-8");
    }
}
