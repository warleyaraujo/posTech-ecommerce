package com.postech.gestaodeenvio;

import com.postech.gestaodeenvio.services.impl.OrdersServiceImpl;
import com.postech.gestaodeenvio.entities.orderentities.Orders;
import com.postech.gestaodeenvio.repository.OrdersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrdersServiceImplTest {

    @Mock
    private OrdersRepository ordersRepository;

    private OrdersServiceImpl ordersService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ordersService = new OrdersServiceImpl(ordersRepository);
    }

    @Test
    public void testSaveOrders() {
        Orders orders = new Orders();

        when(ordersRepository.save(any())).thenReturn(orders);

        Orders savedOrders = ordersService.saveOrders(orders);

        assertEquals(orders, savedOrders);
        verify(ordersRepository, times(1)).save(orders);
    }

    @Test
    public void testFindAllOrders() {
        List<Orders> ordersList = new ArrayList<>();

        when(ordersRepository.findAll()).thenReturn(ordersList);

        List<Orders> foundOrdersList = ordersService.findAllOrders();

        assertEquals(ordersList, foundOrdersList);
        verify(ordersRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteOrders() {
        UUID id = UUID.randomUUID();

        ordersService.deleteOrders(id);

        verify(ordersRepository, times(1)).deleteById(id);
    }
}
