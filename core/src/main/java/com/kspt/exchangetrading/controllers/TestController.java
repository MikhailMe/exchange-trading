package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.repositories.BrokerRepository;
import com.kspt.exchangetrading.repositories.BrokerageAccountRepository;
import com.kspt.exchangetrading.repositories.ClientRepository;
import lombok.var;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

//TODO: don't forget remove me

@RestController
@RequestMapping("test")
public class TestController {

    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public TestController(ClientRepository clientRepository,
                          BrokerRepository brokerRepository,
                          BrokerageAccountRepository brokerageAccountRepository) {
        this.clientRepository = clientRepository;
        this.brokerRepository = brokerRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    @PostMapping("createBroker")
    @ResponseBody
    public Broker create() {
        var broker = new Broker("mikhail", "me");
        return brokerRepository.save(broker);
    }

    @PostMapping("createBA")
    public BrokerageAccount createBA() {
        var ba = new BrokerageAccount(432423L, 3432L, "euro", Instant.now());
        return brokerageAccountRepository.save(ba);
    }

    @GetMapping("getBA/{number}")
    @ResponseBody
    public BrokerageAccount getBA(@PathVariable long number) {
        return brokerageAccountRepository.getByNumber(number).get();
    }

    @RequestMapping("test")
    public String foo() {
        return "lol";
    }

}
