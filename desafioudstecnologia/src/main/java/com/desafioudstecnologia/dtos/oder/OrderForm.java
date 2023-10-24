package com.desafioudstecnologia.dtos.oder;

import jakarta.validation.Valid;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

public record OrderForm(
        @CPF
        String cpf,

        @Valid
        List<OrderedItemForm> orderedItemFormList
) {
}
