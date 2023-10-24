package com.desafioudstecnologia.dtos.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;


public record ClientForm(
        @NotBlank
        String name,
        @CPF
        @NotNull
        String cpf,
        String birthDate
) {

}
