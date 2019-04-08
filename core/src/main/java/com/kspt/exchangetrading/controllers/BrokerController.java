package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.repositories.BrokerRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("broker")
public class BrokerController {

    private final BrokerRepository brokerRepository;

    public BrokerController(BrokerRepository brokerRepository) {
        this.brokerRepository = brokerRepository;
    }

}
