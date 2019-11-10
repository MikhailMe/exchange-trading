package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Passport;
import com.kspt.exchangetrading.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("create")
    public Client create(@RequestBody Client client) {
        return clientService.create(client);
    }

    // contract: passport, currency
    @PostMapping("openBrokerageAccount")
    public void openBrokerageAccount(@RequestBody final Map<String, Object> data) {
        clientService.openBrokerageAccount(data);
    }

    // contract: passport
    @PostMapping("closeBrokerageAccount")
    public void closeBrokerageAccount(@RequestBody final Map<String, Object> data) {
        clientService.closeBrokerageAccount(data);
    }

    /*@PutMapping(value = "update/{id}")
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

    @PostMapping("createBrokerageAccount")
    public void createBrokerageAccount() {

    }*/
}
