package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.client.Client;
import com.desafioudstecnologia.domain.order.Order;
import com.desafioudstecnologia.domain.order.OrderedItem;
import com.desafioudstecnologia.dtos.oder.OrderDTO;
import com.desafioudstecnologia.dtos.oder.OrderForm;
import com.desafioudstecnologia.dtos.oder.OrderedItemDTO;
import com.desafioudstecnologia.dtos.oder.OrderedItemForm;
import com.desafioudstecnologia.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final OrderedItemService orderedItemService;

    public OrderService(OrderRepository orderRepository, ClientService clientService, OrderedItemService orderedItemService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.orderedItemService = orderedItemService;
    }

    @Transactional
    public OrderDTO createOrder(OrderForm orderForm) throws Exception {
        Client client = this.clientService.getClientByCpf(orderForm.cpf());
        Integer number = Math.toIntExact(this.orderRepository.count());
        Order order = new Order(client, number);
        this.orderRepository.save(order);

        BigDecimal sumPrice = BigDecimal.ZERO;
        for(OrderedItemForm orderedItemForm : orderForm.orderedItemFormList()) {
            OrderedItemDTO orderedItemDTO = this.orderedItemService.createOrderedItem(orderedItemForm, order);
            sumPrice = sumPrice.add(orderedItemDTO.total());
        }

        order.update(sumPrice);

        List<OrderedItemDTO> orderedItemDTOList = this.orderedItemService.findAllOrderedItemByNumber(order.getNumber());

        return new OrderDTO(order, orderedItemDTOList);
    }
}
