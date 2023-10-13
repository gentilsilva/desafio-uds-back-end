package com.desafioudstecnologia.dtos.oder;

import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record OrderForm(
        @CPF
        String cpf,
        List<OrderedItemForm> orderedItemFormList
) {
}
