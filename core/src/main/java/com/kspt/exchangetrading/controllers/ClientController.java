package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.services.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("client")
public class ClientController extends AbstractController<Client, ClientService> {

    public ClientController(ClientService clientService) {
        super(clientService);
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

}
