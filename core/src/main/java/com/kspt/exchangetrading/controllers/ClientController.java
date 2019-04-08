package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.repositories.ClientRepository;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientRepository clientRepository;

    public ClientController(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @PostMapping("create")
    @ResponseBody
    public Client create(@RequestBody Client client) {
        return clientRepository.save(client);
    }

    @PutMapping(value = "update/{id}")
    public void update(@PathVariable Long id, @RequestBody Client client) {
        clientRepository.update(client);
    }

    @GetMapping(value = "findById/{id}")
    public void findById(@PathVariable Long id) {
        clientRepository.findById(id).map(client -> {
            client.setName("bam");
            return clientRepository.save(client);
        });
    }

}
