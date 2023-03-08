package org.klim.istock.service;

import org.klim.istock.entity.Client;
import org.klim.istock.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findByEmail(String email) {
        return clientRepository.findByEmail(email).orElse(null);
    }
}
