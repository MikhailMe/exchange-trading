package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.enums.Currency;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Passport;
import com.kspt.exchangetrading.parser.Parser;
import com.kspt.exchangetrading.repositories.BrokerageAccountRepository;
import com.kspt.exchangetrading.repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Objects;

@Service
public class ClientService extends AbstractService<Client, ClientRepository> {

    private final ClientRepository clientRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public ClientService(@NotNull final ClientRepository clientRepository,
                         @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        super(clientRepository);
        this.clientRepository = clientRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    public void openBrokerageAccount(@NotNull final Map<String, Object> data) {
        Passport passport = Parser.parsePassport(data.get(Constants.PASSPORT));
        Client client = clientRepository.findByPassport(Objects.requireNonNull(passport));

        BrokerageAccount brokerageAccount = new BrokerageAccount();
        brokerageAccount.setCurrency(Currency.valueOf(data.get(Constants.CURRENCY).toString()));
        brokerageAccount.setMoney(-100L); // because commission
        brokerageAccount.setCreationDate(Instant.now());

        client.setBrokerageAccount(brokerageAccount);

        clientRepository.save(client);
    }

    public void closeBrokerageAccount(@NotNull final Map<String, Object> data) {
        Passport passport = Parser.parsePassport(data.get(Constants.PASSPORT));
        Client client = clientRepository.findByPassport(Objects.requireNonNull(passport));

        if (client.getBroker() == null) {
            BrokerageAccount ba = client.getBrokerageAccount();

            // FIXME: doesn't remove .. :(
            brokerageAccountRepository.delete(ba);
            client.setBrokerageAccount(null);
            clientRepository.save(client);
        } else {
            // TODO: broker agreement and broker ..... think about it
        }

    }

    public void makeBrokerAgreement() {

    }

    public void extendBrokerAgreement() {

    }

    public void breakBrokerAgreement() {

    }

    public void exchangeMoneyForStocks() {

    }

    public void exchangeStocksForMoney() {

    }
}
