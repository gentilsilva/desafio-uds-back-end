package com.desafioudstecnologia.dtos.user;

import com.desafioudstecnologia.domain.user.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public record UserDTO(
    UUID id,
    String name,
    String cpf,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate birthDate
) {

    public UserDTO(User user) {
        this(user.getId(), user.getName(), user.getCpf(), user.getBirthDate());
    }

}
