package org.klim.istock.service;

import org.klim.istock.entity.Client;
import org.klim.istock.entity.Order;
import org.klim.istock.entity.OrderItem;
import org.klim.istock.model.OrderStatus;
import org.klim.istock.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final OrderItemService orderItemService;

    public OrderService(OrderRepository orderRepository, ClientService clientService, OrderItemService orderItemService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.orderItemService = orderItemService;
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public Order saveNewOrder(Order order) {
        order.setCreated(LocalDateTime.now());
        order.setStatus(OrderStatus.CREATED);
        Client client = order.getClient();
        if (client.getClientId() == null || client.getClientId() == 0) {
            Client savedClient = clientService.save(client);
            order.setClient(savedClient);
        }
        List<OrderItem> orderItems = order.getItems();
        order.setItems(Collections.emptyList());
        Order savedOrder = orderRepository.save(order);
        orderItems.forEach(i -> {
            i.setOrder(savedOrder);
            orderItemService.save(i);
        });
        return orderRepository.findById(savedOrder.getOrderId()).get();
    }
}
