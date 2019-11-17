package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.AbstractController;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.services.actors.BrokerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Actor.BROKER)
public class BrokerController extends AbstractController<Broker, BrokerService> {

    public BrokerController(@NotNull BrokerService service) {
        super(service);
    }

}
