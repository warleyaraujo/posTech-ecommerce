package com.postech.gestaodeenvio.services.impl;

import com.postech.gestaodeenvio.entities.orderentities.Orders;
import com.postech.gestaodeenvio.repository.OrdersRepository;
import com.postech.gestaodeenvio.services.OrdersService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrdersServiceImpl implements OrdersService {

    private final OrdersRepository ordersRepository;

    public OrdersServiceImpl(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Override
    public Orders saveOrders(Orders orders) {
        return ordersRepository.save(orders);
    }

    public List<Orders> findAllOrders() {

        return ordersRepository.findAll();
    }

    @Override
    public void deleteOrders(UUID id) {
        ordersRepository.deleteById(id);
    }
}
