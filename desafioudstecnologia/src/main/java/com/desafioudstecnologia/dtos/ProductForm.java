package com.desafioudstecnologia.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductForm(
        @NotBlank
        String code,
        @NotBlank
        String name,
        @Positive
        @NotNull
        BigDecimal unitaryPrice
) {
}
