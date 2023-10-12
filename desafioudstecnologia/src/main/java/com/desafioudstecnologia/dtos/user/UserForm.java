package com.desafioudstecnologia.dtos.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserForm(
        @NotBlank
        String name,
        @CPF
        String cpf,
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate birthDate
) {
}
