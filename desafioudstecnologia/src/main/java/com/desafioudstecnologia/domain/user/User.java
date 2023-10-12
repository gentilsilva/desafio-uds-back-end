package com.desafioudstecnologia.domain.user;

import com.desafioudstecnologia.dtos.user.UserForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "users")
@Table(name = "tb_users")
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    @Length(max = 255)
    private String name;

    @CPF
    @Column(unique = true)
    @Length(max = 11)
    private String cpf;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    public User(UserForm userForm) {
        this.name = userForm.name();
        this.cpf = userForm.cpf();
        this.birthDate = userForm.birthDate();
    }
}
