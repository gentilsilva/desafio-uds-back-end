package com.desafioudstecnologia.dtos.oder;

import com.desafioudstecnologia.domain.order.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrderDTO(
        UUID id,
        String cpf,
        Integer number,
        LocalDate emission,
        BigDecimal total,
        List<OrderedItemDTO> orderedItem
) {

    public OrderDTO(Order order, List<OrderedItemDTO> orderedItemDTOList) {
        this(order.getId(), order.getClient().getCpf(), order.getNumber(), order.getEmission(), order.getTotal(),
                orderedItemDTOList);
    }

}
