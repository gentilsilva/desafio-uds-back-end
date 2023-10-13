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
    @JoinColumn(name = "client_cpf", referencedColumnName = "cpf")
    private Client client;

    @OneToMany
    private List<OrderedItem> orderedItemList;

    public Order(Client client, Integer total) {
        this.emission = LocalDate.now();
        this.number = ++total;
        this.client = client;
        this.total = BigDecimal.ZERO;
    }

    public void update(BigDecimal sumPrice) {
        this.total = sumPrice;
    }
}
