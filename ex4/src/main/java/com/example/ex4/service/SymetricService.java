package com.example.ex4.service;

import com.example.ex4.dto.EncryptMessage;
import com.example.ex4.model.SymetricKey;
import lombok.SneakyThrows;
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

    @SneakyThrows
    public String generateKey() {
        int keyBitSize = 128;
        keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(keyBitSize);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] raw = secretKey.getEncoded();
        String hexKey = HexUtils.toHexString(raw);
        return hexKey;
    }



    public String encode(EncryptMessage message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] text = new byte[0];
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, symetricKey);
        text = cipher.doFinal(message.getMessage().getBytes());
        return Base64.getEncoder().encodeToString(text);
    }

    public String decode(EncryptMessage message) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] text = new byte[0];
        cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, symetricKey);
        text = cipher.doFinal(Base64.getDecoder().decode(message.getMessage()));

        return new String(text);
    }

    public void setKey(SymetricKey setKeyRequest) {
        byte[] stringKey = setKeyRequest.getKey().getBytes();
        symetricKey = new SecretKeySpec(stringKey,0,stringKey.length,"AES");
    }
}
