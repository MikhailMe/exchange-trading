package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.repositories.ClientRepository;

public class  ClientService implements IClientService {

    private ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository){
        this.clientRepository = clientRepository;
    }

    @Override
    public void openBrokerageAccount() {

    }

    @Override
    public void closeBrokerageAccount() {

    }

    @Override
    public void makeBrokerAgreement() {

    }

    @Override
    public void extendBrokerAgreement() {

    }

    @Override
    public void breakBrokerAgreement() {

    }

    @Override
    public void exchangeMoneyForStocks() {

    }

    @Override
    public void exchangeStocksForMoney() {

    }
}
