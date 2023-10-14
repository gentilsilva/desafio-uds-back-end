package com.desafioudstecnologia.dtos.oder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderedItemForm(
        @NotBlank
        String code,
        @Positive
        Integer amount,
        @PositiveOrZero
        Double discountPercentage
) {
}
