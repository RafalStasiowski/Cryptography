package com.example.ex4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AsymetricKey {
    @NotBlank(message = "Key cannot be empty")
    @NotNull(message = "Key cannot be null")
    private String publicKey;
    @NotBlank(message = "Key cannot be empty")
    @NotNull(message = "Key cannot be null")
    private String privateKey;
}
