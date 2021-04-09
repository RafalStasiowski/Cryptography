package com.example.ex4.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EncryptMessage {
    @NotBlank(message = "Message cannot be blank")
    @NotNull(message = "Message cannot be null")
    private String message;
}
