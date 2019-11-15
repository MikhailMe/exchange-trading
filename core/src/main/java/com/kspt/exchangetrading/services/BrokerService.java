package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.repositories.BrokerRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class BrokerService extends AbstractService<Broker, BrokerRepository> {

    public BrokerService(@NotNull BrokerRepository repository) {
        super(repository);
    }
}
