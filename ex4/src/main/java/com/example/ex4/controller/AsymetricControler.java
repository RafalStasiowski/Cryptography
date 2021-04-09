package com.example.ex4.controller;

import com.example.ex4.model.AsymetricKey;
import com.example.ex4.service.AsymetricService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/asymetric/")
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
    public ResponseEntity<String> setKeys(AsymetricKey asymetricKey) {


        return new ResponseEntity("Error has occured", HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
