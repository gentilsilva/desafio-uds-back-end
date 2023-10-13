package com.desafioudstecnologia.domain.order;

import com.desafioudstecnologia.domain.client.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity(name = "orders")
@Table(name = "tb_orders")
@NoArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private Integer number;
    private LocalDate emission;
    private BigDecimal total;

    @ManyToOne
    @JoinColumn(name = "client_cpf", columnDefinition = "cpf")
    private Client client;

    @OneToMany
    private List<OrderedItem> orderedItemList;

}
