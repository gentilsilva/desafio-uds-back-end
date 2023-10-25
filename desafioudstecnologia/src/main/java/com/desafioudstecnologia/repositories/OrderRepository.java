package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    Optional<Order> findOrderByNumber(Integer number);

    List<Order> findAllUsersByClientCpfOrNumberOrEmissionOrTotal(String clientCpf, Integer number, LocalDate date, BigDecimal total);
}
