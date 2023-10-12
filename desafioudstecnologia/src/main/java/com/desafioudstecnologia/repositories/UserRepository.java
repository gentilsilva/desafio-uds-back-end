package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    List<User> findAllUsersByNameOrCpfOrBirthDate(String name, String cpf, LocalDate birthDate);
}
