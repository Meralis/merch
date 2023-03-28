package org.klim.istock.service;

import org.klim.istock.entity.Client;
import org.klim.istock.entity.Order;
import org.klim.istock.model.OrderStatus;
import org.klim.istock.repository.OrderRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final EmailService emailService;

    public OrderService(OrderRepository orderRepository, ClientService clientService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.emailService = emailService;
    }

    @Transactional
    public Order save(Order order) {
        if (order.getCreated() == null) {
            order.setCreated(LocalDateTime.now());
        }
        if (order.getStatus() == null) {
            order.setStatus(OrderStatus.CREATED);
        }
        Client client = order.getClient();
        if (client.getClientId() == null || client.getClientId() == 0) {
            Client savedClient = clientService.save(client);
            order.setClient(savedClient);
        }
        Order savedOrder = orderRepository.save(order);
        emailService.sendEmail(order.getClient().getEmail(),
                "Ваше замовлення №" + order.getOrderId() + " прийнято",
                emailService.buildMessageBody(order.getItems()));
        return savedOrder;
    }
}
