package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("client")
public class ClientController extends AbstractController<Client, ClientService> {

    public ClientController(ClientService clientService) {
        super(clientService);
    }

    // contract: login, password, client(name, surname, personType)
    @PostMapping("signUp")
    public Client createNewClient(@RequestBody final Map<String, Object> data) {
        return service.signUp(data);
    }

    // contract: credentials
    @PostMapping("signIn")
    public boolean signIn(@RequestBody final Credentials credentials) {
        return service.signIn(credentials);
    }

    // contract: clientId
    @PostMapping("signOut/{clientId}")
    public boolean signOut(@PathVariable final Long clientId) {
        return service.signOut(clientId);
    }

    // contract: clientId, currency
    @PostMapping("openBrokerageAccount")
    public boolean openBrokerageAccount(@RequestBody final Map<String, Object> data) {
        return service.openBrokerageAccount(data);
    }

    // contract: clientId
    @PostMapping("closeBrokerageAccount")
    public boolean closeBrokerageAccount(@RequestBody final Map<String, Object> data) {
        return service.closeBrokerageAccount(data);
    }

    // contract: clientId, brokerageAccountId, money
    @PostMapping("putMoneyToAccount")
    public boolean putMoneyToAccount(@RequestBody final Map<String, Object> data) {
        return service.putMoneyToAccount(data);
    }

    // contract: clientId, validity
    @PostMapping("makeBrokerAgreement")
    public Agreement makeBrokerAgreement(@RequestBody final Map<String, Object> data) {
        return service.makeBrokerAgreement(data);
    }

    @PostMapping("breakBrokerAgreement/{clientId}")
    public boolean breakBrokerAgreement(@PathVariable final Long clientId) {
        return service.breakBrokerAgreement(clientId);
    }
}
