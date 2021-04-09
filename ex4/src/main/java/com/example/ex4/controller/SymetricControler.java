package com.example.ex4.controller;

import com.example.ex4.dto.EncryptMessage;
import com.example.ex4.model.SymetricKey;
import com.example.ex4.service.SymetricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.validation.Valid;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@RestController
@RequestMapping("/symmetric/")
@AllArgsConstructor
@Validated
public class SymetricControler {

    public final SymetricService symetricService;

    @GetMapping("key")
    public String generateKey() throws NoSuchAlgorithmException {
        return symetricService.generateKey();
    }

    @PostMapping("key")
    public ResponseEntity<String> setKey(@Valid @RequestBody SymetricKey setKeyRequest) {
        symetricService.setKey(setKeyRequest);
        return new ResponseEntity<>("Key successfully set", HttpStatus.OK);
    }

    @PostMapping("encode")
    public ResponseEntity<String> encode(@RequestBody EncryptMessage message) {
        try {
            return new ResponseEntity<>(symetricService.encode(message),HttpStatus.OK);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("decode")
    public ResponseEntity<String> decode(@RequestBody EncryptMessage message) {
        try {
            return new ResponseEntity<>(symetricService.decode(message),HttpStatus.OK);
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occurred" , HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occurred" , HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
            return new ResponseEntity("Invalid key" , HttpStatus.BAD_REQUEST);
        } catch (BadPaddingException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occurred" , HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
            return new ResponseEntity("Error has occurred" , HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
