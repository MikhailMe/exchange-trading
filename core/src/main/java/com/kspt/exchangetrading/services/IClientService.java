package com.kspt.exchangetrading.services;

public interface IClientService {

    void openBrokerageAccount();

    void closeBrokerageAccount();

    void makeBrokerAgreement();

    void extendBrokerAgreement();

    void breakBrokerAgreement();

    void exchangeMoneyForStocks();

    void exchangeStocksForMoney();
}
