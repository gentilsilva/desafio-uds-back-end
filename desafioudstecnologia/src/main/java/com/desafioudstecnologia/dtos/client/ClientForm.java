package com.desafioudstecnologia.dtos.client;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record ClientForm(
        @NotBlank
        String name,
        @CPF
        String cpf,
        String birthDate
) {

}
