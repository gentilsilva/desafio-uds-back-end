package com.desafioudstecnologia.dtos.oder;

import com.desafioudstecnologia.domain.order.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderDTO(
        String cpf,
        Integer number,
        LocalDate emission,
        BigDecimal total,
        List<OrderedItemDTO> orderedItemDTO
) {

    public OrderDTO(Order order) {
        this(order.getClient().getCpf(), order.getNumber(), order.getEmission(), order.getTotal(),
                order.getOrderedItemList().stream().map(OrderedItemDTO::new).toList());
    }

}
