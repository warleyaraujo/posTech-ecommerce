package com.postech.gestaodeenvio.controllers;

import com.postech.gestaodeenvio.entities.orderentities.Orders;
import com.postech.gestaodeenvio.services.OrdersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/orders")
public class OrdersController {

    private final OrdersService ordersService;

    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Orders>> getOrders() {
        List<Orders> orders = ordersService.findAllOrders();
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public void deleterOrders(@PathVariable UUID id) {
        ordersService.deleteOrders(id);
    }
}