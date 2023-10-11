package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.ProductDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findProductByCode(String code);

    Optional<Product> findProductByName(String name);

    Optional<List<Product>> findAllProductByUnitaryPrice(BigDecimal unitaryPrice);
}
