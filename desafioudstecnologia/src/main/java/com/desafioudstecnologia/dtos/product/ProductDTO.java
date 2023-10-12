package com.desafioudstecnologia.dtos.product;

import com.desafioudstecnologia.domain.product.Product;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductDTO(
        UUID id,
        String code,
        String name,
        BigDecimal unitaryPrice
) {

    public ProductDTO(Product product) {
        this(product.getId(), product.getCode(), product.getName(), product.getUnitaryPrice());
    }

}
