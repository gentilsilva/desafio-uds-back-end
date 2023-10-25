package com.desafioudstecnologia.services;

import com.desafioudstecnologia.domain.client.Client;
import com.desafioudstecnologia.domain.order.Order;
import com.desafioudstecnologia.dtos.oder.OrderDTO;
import com.desafioudstecnologia.dtos.oder.OrderForm;
import com.desafioudstecnologia.dtos.oder.OrderedItemDTO;
import com.desafioudstecnologia.dtos.oder.OrderedItemForm;
import com.desafioudstecnologia.exceptions.RecordNotFoundException;
import com.desafioudstecnologia.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
    public OrderDTO createOrder(OrderForm orderForm) {
        Client client = this.clientService.getClientByCpf(orderForm.cpf());
        Integer number = Math.toIntExact(this.orderRepository.count());
        Order order = new Order(client, number);
        this.orderRepository.save(order);

        createOrderedItem(orderForm.orderedItemFormList(), order);
        updatePrice(order);

        List<OrderedItemDTO> orderedItemDTOList = this.orderedItemService.findAllOrderedItemByNumber(order.getNumber());
        return new OrderDTO(order, orderedItemDTOList);
    }

    public void createOrderedItem(List<OrderedItemForm> orderedItemFormList, Order order) {
        for(OrderedItemForm orderedItemForm : orderedItemFormList) {
            this.orderedItemService.createOrderedItem(orderedItemForm, order);
        }
    }

    public List<OrderDTO> getOrdersByParams(String clientCpf, Integer number, String date, BigDecimal total) {
        LocalDate emissionDate = null;
        if(!(date == null)) {
            emissionDate = FormatDate.searchFormat(date);
        }
        List<Order> orderList = this.orderRepository.findAllUsersByClientCpfOrNumberOrEmissionOrTotal(clientCpf, number, emissionDate, total);
        List<OrderedItemDTO> orderedItemDTOList = orderList.stream().map(order -> this.orderedItemService.findAllOrderedItemByNumber(order.getNumber()))
                .flatMap(List::stream).toList();


        return orderList.stream().map(order -> {
                    List<OrderedItemDTO> itemDTOList = orderedItemDTOList.stream().filter(
                            orderedItemDTO -> Objects.equals(orderedItemDTO.orderNumber(), order.getNumber())).toList();
            return new OrderDTO(order, itemDTOList);
        }).toList();
    }

    public void updatePrice(Order order) {
        BigDecimal sumPrice = this.orderedItemService.sumOrderedItemsPriceByNumber(order.getNumber());
        order.update(sumPrice);
    }

    public void deleteOrder(Integer number) {
        Order order = this.orderRepository.findOrderByNumber(number)
                .orElseThrow(() -> new RecordNotFoundException(String.valueOf(number)));
        this.orderRepository.delete(order);
    }
}
