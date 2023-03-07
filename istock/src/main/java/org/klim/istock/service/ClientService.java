package org.klim.istock.service;

import org.klim.istock.entity.Client;
import org.klim.istock.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client findByEmail(String email) {
        Optional<Client> optionalClient = clientRepository.findAll()
                .stream()
                .filter(cl -> cl.getEmail().equals(email))
                .findFirst();
        if (optionalClient.isEmpty()) {
            return null;
        }
        return optionalClient.get();
    }
}
