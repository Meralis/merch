package org.klim.istock.controller;

import org.klim.istock.DTO.OrderDTO;
import org.klim.istock.service.ClientService;
import org.klim.istock.util.ModelMapperUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    public final ClientService clientService;
    private final ModelMapperUtil modelMapper;

    public OrderController(ClientService clientService, ModelMapperUtil modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @PutMapping("/order")
    public void buildOrder(@RequestBody OrderDTO orderDTO) {
         orderDTO.setComments("aaa");
    }
}
