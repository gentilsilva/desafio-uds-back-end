package com.desafioudstecnologia.domain.client;

import com.desafioudstecnologia.domain.order.Order;
import com.desafioudstecnologia.dtos.client.ClientForm;
import com.desafioudstecnologia.services.FormatDate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;
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

    @OneToMany(mappedBy = "client")
    private List<Order> orderList;

    public Client(ClientForm clientForm) {
        this.name = clientForm.name();
        this.cpf = clientForm.cpf();
        this.birthDate = FormatDate.registerFormat(clientForm.birthDate());
    }

    public void update(ClientForm clientForm) {
        if(!clientForm.name().isEmpty()) {
            this.name = clientForm.name();
        }
        if(!clientForm.cpf().isEmpty()) {
            this.cpf = clientForm.cpf();
        }
        if(!(clientForm.birthDate() == null)) {
            this.birthDate = FormatDate.registerFormat(clientForm.birthDate());
        }
    }
}
