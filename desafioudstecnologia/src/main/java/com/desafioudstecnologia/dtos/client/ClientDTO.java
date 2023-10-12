package com.desafioudstecnologia.dtos.client;

import com.desafioudstecnologia.domain.client.Client;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.UUID;

public record ClientDTO(
    UUID id,
    String name,
    String cpf,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate birthDate
) {

    public ClientDTO(Client client) {
        this(client.getId(), client.getName(), client.getCpf(), client.getBirthDate());
    }

}
