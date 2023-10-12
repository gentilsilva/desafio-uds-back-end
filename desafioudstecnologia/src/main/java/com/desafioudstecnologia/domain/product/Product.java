package com.desafioudstecnologia.domain.product;

import com.desafioudstecnologia.dtos.product.ProductForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.UUID;

@Entity(name = "products")
@Table(name = "tb_products")
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    @Length(max = 255)
    private String code;

    @Column(unique = true)
    @Length(max = 255)
    private String name;

    @Column(name = "unitary_price")
    private BigDecimal unitaryPrice;

    public Product(ProductForm productForm) {
        this.code = productForm.code();
        this.name = productForm.name();
        this.unitaryPrice = productForm.unitaryPrice();
    }

    public void update(ProductForm productForm) {
        if(!productForm.code().isEmpty()) {
            this.code = productForm.code();
        }
        if(!productForm.name().isEmpty()) {
            this.name = productForm.name();
        }
        if(this.unitaryPrice.signum() > 0) {
            this.unitaryPrice = productForm.unitaryPrice();
        }
    }
}
