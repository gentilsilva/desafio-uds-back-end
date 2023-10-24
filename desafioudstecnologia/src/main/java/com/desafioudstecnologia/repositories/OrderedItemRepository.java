package com.desafioudstecnologia.repositories;

import com.desafioudstecnologia.domain.order.OrderedItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface OrderedItemRepository extends JpaRepository<OrderedItem, UUID> {

    @Query(value = "SELECT * FROM TB_ORDERED_ITEMS WHERE TB_ORDERED_ITEMS.order_number = :number", nativeQuery = true)
    List<OrderedItem> findAllOrderedItems(Integer number);

    @Query(value = "SELECT SUM(o.total) FROM TB_ORDERED_ITEMS o WHERE o.order_number = :number", nativeQuery = true)
    BigDecimal sumOrderedItemUnitaryPrice(Integer number);
}
