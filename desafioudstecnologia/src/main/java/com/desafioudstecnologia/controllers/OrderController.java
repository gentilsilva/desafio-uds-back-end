package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.oder.OrderDTO;
import com.desafioudstecnologia.dtos.oder.OrderForm;
import com.desafioudstecnologia.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderForm orderForm, UriComponentsBuilder uriComponentsBuilder)
            throws Exception {
        OrderDTO orderDTO = this.orderService.createOrder(orderForm);
        URI uri = uriComponentsBuilder.path("/orders/{id}").buildAndExpand(orderDTO.id()).toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }

}
