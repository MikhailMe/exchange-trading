package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.util.enums.Validity;

import java.time.Instant;

public class Agreement {

    private long id;

    private Client client;

    private Broker broker;

    private Validity validity;

    private Instant startDate;

    private Instant endDate;

    private BrokerageAccount brokerageAccount;
}
