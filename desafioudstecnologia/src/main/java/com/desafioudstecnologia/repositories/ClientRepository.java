package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    List<Client> findAllUsersByNameOrCpfOrBirthDate(String name, String cpf, LocalDate birthDate);

    Optional<Client> findClientByCpf(String cpf);
}
