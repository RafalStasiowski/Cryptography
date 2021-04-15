package com.example.ex4.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SymetricKey {
    @NotBlank(message = "Key cannot be empty")
    @NotNull(message = "Key cannot be null")
    @Size(min = 16, max = 128, message = "Invalid key")
    private String key;

}
