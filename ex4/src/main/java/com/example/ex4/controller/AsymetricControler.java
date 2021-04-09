package com.example.ex4.controller;

import com.example.ex4.dto.EncryptMessage;
import com.example.ex4.dto.SignatureMessage;
import com.example.ex4.model.AsymetricKey;
import com.example.ex4.service.AsymetricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/asymmetric/")
@AllArgsConstructor
@Validated
public class AsymetricControler {

    AsymetricService asymetricService;

    @GetMapping("key")
    public ResponseEntity<String> generateKeys() {

        try {
            Map<String, Object> keys = new HashMap<>();
            asymetricService.generateKeys();
            keys.put("private", asymetricService.getPrivateKey().getEncoded());//HexUtils.toHexString(asymetricService.getPrivateKey().getEncoded()));
            keys.put("public", asymetricService.getPublicKey().getEncoded());//HexUtils.toHexString(asymetricService.getPublicKey().getEncoded()));
            return new ResponseEntity(keys, HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity("No algorithm error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("key")
    public ResponseEntity<String> setKeys(@RequestBody AsymetricKey asymetricKey) {
        try {
            asymetricService.setKeys(asymetricKey);
            return new ResponseEntity("Keys has been updated", HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("verify")
    public ResponseEntity<String> verify(@RequestBody SignatureMessage message) {
        try {
            return new ResponseEntity(asymetricService.verify(message.getMessage(), message.getSignature(), asymetricService.getPublicKey()), HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SignatureException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("sign")
    public ResponseEntity<String> sign(@RequestBody EncryptMessage message) {
        try {
            return new ResponseEntity(asymetricService.sign(message.getMessage(),asymetricService.getPrivateKey()), HttpStatus.OK);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (SignatureException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("encrypt")
    public ResponseEntity<String> encrypt(@RequestBody EncryptMessage message, PublicKey publicKey) {
        try {
            return new ResponseEntity(asymetricService.encrypt(message.getMessage(),publicKey),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("decrypt")
    public ResponseEntity<String> decrypt(@RequestBody EncryptMessage message, PrivateKey privateKey) {
        try {
            return new ResponseEntity(asymetricService.decrypt(message.getMessage(),privateKey),HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
