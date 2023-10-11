package com.desafioudstecnologia.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductForm(
        String code,
        @NotBlank
        String name,
        @Positive
        BigDecimal unitaryPrice
) {
}
