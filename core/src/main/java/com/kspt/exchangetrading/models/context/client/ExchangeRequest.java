package com.kspt.exchangetrading.models.context.client;


import lombok.Data;

@Data
public final class ExchangeRequest {

    private long numberBrokerageAccount;

    private long money;

}
