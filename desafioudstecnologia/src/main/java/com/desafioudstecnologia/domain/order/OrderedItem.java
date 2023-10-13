package com.desafioudstecnologia.domain.order;

import com.desafioudstecnologia.domain.product.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "orderedItems")
@Table(name = "tb_ordered_items")
@NoArgsConstructor
@Getter
public class OrderedItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private Integer amount;

    @Column(name = "unitary_price")
    private BigDecimal unitaryPrice;

    @Column(name = "discount_percentage")
    private Double discountPercentage;
    private BigDecimal total;

    @OneToOne
    @JoinColumn(name = "product_code", referencedColumnName = "code")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_number", referencedColumnName = "number")
    private Order order;


}
