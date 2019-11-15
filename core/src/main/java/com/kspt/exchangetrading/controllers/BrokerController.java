package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.services.BrokerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("broker")
public class BrokerController extends AbstractController<Broker, BrokerService> {

    public BrokerController(@NotNull BrokerService service) {
        super(service);
    }

}
