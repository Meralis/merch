package org.klim.istock.controller;

import org.klim.istock.DTO.ClientDTO;
import org.klim.istock.entity.Client;
import org.klim.istock.service.ClientService;
import org.klim.istock.util.ModelMapperUtil;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${app.url}")
public class ClientController {
    public final ClientService clientService;
    private final ModelMapperUtil modelMapper;

    public ClientController(ClientService clientService, ModelMapperUtil modelMapper) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/signIn")
    public ClientDTO findByEmail(String email) {
        return toDto(clientService.findByEmail(email));
    }

    private ClientDTO toDto(Client client) {
        return modelMapper.map(client, ClientDTO.class);
    }
}
