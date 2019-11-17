package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.services.AbstractService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class BrokerService extends AbstractService<Broker, BrokerRepository> {

    public BrokerService(@NotNull BrokerRepository repository) {
        super(repository);
    }
}
