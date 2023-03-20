package com.example.restservice.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record Content(
        Integer id,
        @NotBlank //Validation
        String content) {

}
