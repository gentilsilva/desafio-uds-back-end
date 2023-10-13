package com.desafioudstecnologia.dtos.oder;

public record OrderedItemForm(
        String code,
        Integer amount,
        Double discountPercentage
) {
}
