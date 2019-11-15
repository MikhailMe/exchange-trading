package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.enums.Currency;
import com.kspt.exchangetrading.enums.Validity;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.repositories.AgreementRepository;
import com.kspt.exchangetrading.repositories.BrokerRepository;
import com.kspt.exchangetrading.repositories.BrokerageAccountRepository;
import com.kspt.exchangetrading.repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
public class ClientService extends AbstractService<Client, ClientRepository> {

    private final AggregationService aggregationService;

    private final BrokerRepository brokerRepository;

    private final AgreementRepository agreementRepository;

    private final BrokerageAccountRepository brokerageAccountRepository;

    public ClientService(@NotNull final ClientRepository clientRepository,
                         @NotNull final BrokerRepository brokerRepository,
                         @NotNull final AggregationService aggregationService,
                         @NotNull final AgreementRepository agreementRepository,
                         @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        super(clientRepository);
        this.brokerRepository = brokerRepository;
        this.aggregationService = aggregationService;
        this.agreementRepository = agreementRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    public boolean openBrokerageAccount(@NotNull final Map<String, Object> data) {
        final long clientId = Long.parseLong(data.get("clientId").toString());
        Client client = repository.findById(clientId).get();

        if (client.getBrokerageAccount() == null) {
            BrokerageAccount brokerageAccount = new BrokerageAccount();
            brokerageAccount.setCurrency(Currency.valueOf(data.get(Constants.CURRENCY).toString()));
            brokerageAccount.setMoney(-100L); // because commission
            brokerageAccount.setCreationDate(Instant.now());

            client.setBrokerageAccount(brokerageAccount);
            repository.save(client);
            return true;
        }
        return false;
    }

    public boolean closeBrokerageAccount(@NotNull final Map<String, Object> data) {
        final long clientId = Long.parseLong(data.get("clientId").toString());
        Client client = repository.findById(clientId).get();

        if (client.getBroker() == null && client.getAgreement() == null) {
            BrokerageAccount brokerageAccount = client.getBrokerageAccount();
            if (brokerageAccountRepository.existsById(brokerageAccount.getId())) {
                brokerageAccountRepository.deleteById(brokerageAccount.getId());
                client.setBrokerageAccount(null);
                repository.save(client);
                return true;
            }
        }
        return false;
    }

    public boolean putMoneyToAccount(@NotNull final Map<String, Object> data) {
        final long clientId = Long.parseLong(data.get("clientId").toString());
        final long brokerageAccountId = Long.parseLong(data.get("brokerageAccountId").toString());
        final long money = Long.parseLong(data.get("money").toString());

        if (!brokerageAccountRepository.existsById(brokerageAccountId)) {
            return false;
        }

        Client client = repository.findById(clientId).get();
        BrokerageAccount brokerageAccount = client.getBrokerageAccount();

        if (brokerageAccount.getId() == brokerageAccountId) {
            long currentBalance = brokerageAccount.getMoney();
            brokerageAccount.setMoney(currentBalance + money);
            client.setBrokerageAccount(brokerageAccount);
            this.update(clientId, client);
            return true;
        }
        return false;
    }

    public Agreement makeBrokerAgreement(@NotNull final Map<String, Object> data) {
        final long clientId = Long.parseLong(data.get("clientId").toString());
        final Validity validity = Validity.valueOf(data.get("validity").toString());

        final Broker vacantBroker = aggregationService.getVacantBroker(clientId);

        final Client client = repository.findById(clientId).get();

        // create agreement
        final Agreement agreement = new Agreement();
        agreement.setStartDate(Instant.now());
        agreement.setValidity(validity.toString());
        agreementRepository.save(agreement);

        // update broker
        List<Agreement> brokerAgreements = vacantBroker.getAgreements();
        if (brokerAgreements == null) {
            brokerAgreements = Collections.singletonList(agreement);
        } else if (!brokerAgreements.contains(agreement)) {
            brokerAgreements.add(agreement);
        }
        vacantBroker.setAgreements(brokerAgreements);
        brokerRepository.save(vacantBroker);

        // update client
        client.setAgreement(agreement);
        repository.save(client);

        return agreement;
    }

    public Agreement extendBrokerAgreement() {
        return new Agreement();
    }

    public boolean breakBrokerAgreement() {
        return true;
    }

    public void exchangeMoneyForStocks() {

    }

    public void exchangeStocksForMoney() {

    }
}
