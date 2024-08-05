package com.postech.gestaodeenvio.services;

import com.postech.gestaodeenvio.entities.orderentities.Orders;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrdersService {

    public Orders saveOrders(Orders orders);

    public List<Orders> findAllOrders();

    public void deleteOrders(UUID id);

}
