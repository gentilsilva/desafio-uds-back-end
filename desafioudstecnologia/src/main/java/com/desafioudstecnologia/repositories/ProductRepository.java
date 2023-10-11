package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByCode(String code);

    Optional<Product> findProductByName(String name);

    List<Product> findAllProductByCodeOrNameOrUnitaryPrice(String code, String name, BigDecimal unitaryPrice);
}
