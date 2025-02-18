package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.order.Order;
import com.desafioudstecnologia.domain.order.OrderedItem;
import com.desafioudstecnologia.domain.product.Product;
import com.desafioudstecnologia.dtos.oder.OrderedItemDTO;
import com.desafioudstecnologia.dtos.oder.OrderedItemForm;
import com.desafioudstecnologia.repositories.OrderedItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderedItemService {

    private final OrderedItemRepository orderedItemRepository;
    private final ProductService productService;

    public OrderedItemService(OrderedItemRepository orderedItemRepository, ProductService productService) {
        this.orderedItemRepository = orderedItemRepository;
        this.productService = productService;
    }

    @Transactional
    public void createOrderedItem(OrderedItemForm orderedItemForm, Order order) {
        Product product = this.productService.getProductByCode(orderedItemForm.code());
        OrderedItem orderedItem = new OrderedItem(orderedItemForm, product, order);
        this.orderedItemRepository.save(orderedItem);
    }


    @Transactional(readOnly = true)
    public List<OrderedItemDTO> findAllOrderedItemByNumber(Integer number) {
        List<OrderedItem> orderedItemList = this.orderedItemRepository.findAllOrderedItems(number);
        return orderedItemList.stream().map(OrderedItemDTO::new).toList();
    }

    public BigDecimal sumOrderedItemsPriceByNumber(Integer number) {
        return this.orderedItemRepository.sumOrderedItemUnitaryPrice(number);
    }
}
