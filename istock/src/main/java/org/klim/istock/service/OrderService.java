package org.klim.istock.service;

import org.klim.istock.entity.Client;
import org.klim.istock.entity.Order;
import org.klim.istock.model.OrderStatus;
import org.klim.istock.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Locale;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ClientService clientService;
    private final EmailService emailService;

    @Value("${spring.mail.username}")
    private String from;

    public OrderService(OrderRepository orderRepository, ClientService clientService, EmailService emailService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.emailService = emailService;
    }

    @Transactional
    public Order save(Order order) throws MessagingException {
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

        emailService.sendOrderConfirmation(
                from,
                order.getClient().getEmail(),
                "Ваше замовлення №" + order.getOrderId() + " прийнято",
                new Locale.Builder().setLanguageTag("en-US").build(),
                order);
        return savedOrder;
    }
}
