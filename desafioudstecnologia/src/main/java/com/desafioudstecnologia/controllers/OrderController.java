package com.desafioudstecnologia.controllers;

import com.desafioudstecnologia.dtos.oder.OrderDTO;
import com.desafioudstecnologia.dtos.oder.OrderForm;
import com.desafioudstecnologia.services.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody @Valid OrderForm orderForm, UriComponentsBuilder uriComponentsBuilder) {
        OrderDTO orderDTO = this.orderService.createOrder(orderForm);
        URI uri = uriComponentsBuilder.path("/orders/{id}").buildAndExpand(orderDTO.id()).toUri();
        return ResponseEntity.created(uri).body(orderDTO);
    }

    @GetMapping("/search")
    public ResponseEntity<List<OrderDTO>> getOrdersByParams(@RequestParam(required = false) String clientCpf,
                                                            @RequestParam(required = false) Integer number,
                                                            @RequestParam(required = false) String date,
                                                            @RequestParam(required = false)BigDecimal total) {
        List<OrderDTO> orderDTOList = this.orderService.getOrdersByParams(clientCpf, number, date, total);
        return ResponseEntity.ok().body(orderDTOList);
    }

    @DeleteMapping("/{number}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer number) {
        this.orderService.deleteOrder(number);
        return ResponseEntity.noContent().build();
    }

}
