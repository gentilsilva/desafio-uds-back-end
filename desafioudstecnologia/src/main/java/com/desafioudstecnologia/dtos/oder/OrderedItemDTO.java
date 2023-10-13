package com.desafioudstecnologia.dtos.oder;

import com.desafioudstecnologia.domain.order.OrderedItem;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderedItemDTO(
        UUID id,
        Integer amount,
        BigDecimal unitaryPrice,
        Double discountPercentage,
        BigDecimal total,
        String code
) {

    public OrderedItemDTO(OrderedItem orderedItem) {
        this(orderedItem.getId(), orderedItem.getAmount(), orderedItem.getUnitaryPrice(), orderedItem.getDiscountPercentage(),
                orderedItem.getTotal(), orderedItem.getProduct().getCode());
    }

}
