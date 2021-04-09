package com.example.ex4.service;

import com.example.ex4.dto.EncryptMessage;
import com.example.ex4.model.SymetricKey;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Service;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;


@Service
public class SymetricService {

    private KeyGenerator keyGenerator;
    private Cipher cipher;
    private SecretKey symetricKey;


    /**
     * Function that generate symetric key
     * @return String with symetric key
     */
    public String generateKey() throws NoSuchAlgorithmException {
        int keyBitSize = 128;
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keyBitSize);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] raw = secretKey.getEncoded();
        String hexKey = HexUtils.toHexString(raw);
        return hexKey;
    }


    /**
     * Funciton that Encode message with symetric key
     * @param message String value with message to encode
     * @return String with encoded message
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String encode(EncryptMessage message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] text = new byte[0];
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, symetricKey);
        text = cipher.doFinal(message.getMessage().getBytes());
        return Base64.getEncoder().encodeToString(text);
    }

    /**
     * Function that decode encoded message
     * @param message String with encoded message
     * @return
     * @throws NoSuchPaddingException
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     * @throws BadPaddingException
     * @throws IllegalBlockSizeException
     */
    public String decode(EncryptMessage message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] text = new byte[0];
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, symetricKey);
        text = cipher.doFinal(Base64.getDecoder().decode(message.getMessage()));

        return new String(text);
    }

    /**
     * Function that set symetric key
     * @param setKeyRequest SymetricKey object that values will be set
     */
    public void setKey(SymetricKey setKeyRequest) {
        byte[] stringKey = setKeyRequest.getKey().getBytes();
        symetricKey = new SecretKeySpec(stringKey,0,stringKey.length,"AES");
    }
}
