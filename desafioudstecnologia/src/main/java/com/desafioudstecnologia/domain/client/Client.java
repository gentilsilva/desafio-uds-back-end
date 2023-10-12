package com.desafioudstecnologia.domain.client;

import com.desafioudstecnologia.dtos.client.ClientForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "clients")
@Table(name = "tb_clients")
@NoArgsConstructor
@Getter
public class Client {

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

    public Client(ClientForm clientForm) {
        this.name = clientForm.name();
        this.cpf = clientForm.cpf();
        this.birthDate = clientForm.birthDate();
    }

    public void update(ClientForm client) {
        if(!client.name().isEmpty()) {
            this.name = client.name();
        }
        if(!client.cpf().isEmpty()) {
            this.cpf = client.cpf();
        }
        if(!(client.birthDate() == null)) {
            this.birthDate = client.birthDate();
        }
    }
}
