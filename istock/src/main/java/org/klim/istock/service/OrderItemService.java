package org.klim.istock.service;

import org.klim.istock.entity.OrderItem;
import org.klim.istock.repository.OrderItemsRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {
    private final OrderItemsRepository orderItemsRepository;

    public OrderItemService(OrderItemsRepository orderItemsRepository) {
        this.orderItemsRepository = orderItemsRepository;
    }

    public OrderItem save(OrderItem orderItem) {
        return orderItemsRepository.save(orderItem);
    }
}
