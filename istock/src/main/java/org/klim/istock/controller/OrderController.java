package org.klim.istock.controller;

import org.klim.istock.DTO.OrderDTO;
import org.klim.istock.converter.OrderConverter;
import org.klim.istock.entity.Client;
import org.klim.istock.entity.Order;
import org.klim.istock.model.ClientStatus;
import org.klim.istock.service.ClientService;
import org.klim.istock.service.OrderService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;

@RestController
@CrossOrigin(origins = "${app.url}")
public class OrderController {
    public final ClientService clientService;
    private final OrderConverter orderConverter;
    private final OrderService orderService;

    public OrderController(ClientService clientService, OrderConverter orderConverter, OrderService orderService) {
        this.clientService = clientService;
        this.orderConverter = orderConverter;
        this.orderService = orderService;
    }

    @PutMapping("/order")
    public OrderDTO buildOrder(@RequestBody OrderDTO orderDTO) throws MessagingException {
        Order order = orderConverter.toEntity(orderDTO);
        Client client = order.getClient();
        if (client.getClientId() == null) {
            client.setStatus(ClientStatus.CREATED);
        }
        Order savedOrder = orderService.save(order);
        return orderConverter.toDto(savedOrder);
    }
}
