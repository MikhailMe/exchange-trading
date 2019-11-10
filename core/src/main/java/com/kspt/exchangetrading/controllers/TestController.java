package com.kspt.exchangetrading.controllers;

import org.springframework.web.bind.annotation.*;

//TODO: don't forget remove me

@RestController
@RequestMapping("test")
public class TestController {

   /* private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public TestController(ClientRepository clientRepository,
                          BrokerRepository brokerRepository,
                          BrokerageAccountRepository brokerageAccountRepository) {
        this.clientRepository = clientRepository;
        this.brokerRepository = brokerRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }
*/

    @GetMapping("test")
    public String foo() {
        return "lol";
    }

}
