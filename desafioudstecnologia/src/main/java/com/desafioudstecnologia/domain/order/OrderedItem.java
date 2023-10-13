package com.desafioudstecnologia.domain.order;

import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.oder.OrderedItemForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    public OrderedItem(OrderedItemForm orderedItemForm, Product product, Order order) {
        this.amount = orderedItemForm.amount();
        this.unitaryPrice = product.getUnitaryPrice();
        this.discountPercentage = orderedItemForm.discountPercentage();
        this.total = totalCalculation(orderedItemForm, product);
        this.product = product;
        this.order = order;
    }

    private BigDecimal totalCalculation(OrderedItemForm orderedItemForm, Product product) {
        BigDecimal total = product.getUnitaryPrice().multiply(new BigDecimal(orderedItemForm.amount()));
        BigDecimal discount = total.multiply(BigDecimal.valueOf(orderedItemForm.discountPercentage())).
                divide(BigDecimal.valueOf(100), RoundingMode.CEILING);
        return total.subtract(discount);
    }
}
