package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.models.system.Passport;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.repositories.system.BrokerageAccountRepository;
import com.kspt.exchangetrading.services.AbstractService;
import com.kspt.exchangetrading.services.RequestService;
import com.kspt.exchangetrading.services.SystemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class ClientService extends AbstractService<Client, ClientRepository> {

    private final SystemService systemService;
    private final RequestService requestService;
    private final BrokerRepository brokerRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public ClientService(@NotNull final SystemService systemService,
                         @NotNull final RequestService requestService,
                         @NotNull final ClientRepository clientRepository,
                         @NotNull final BrokerRepository brokerRepository,
                         @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        super(clientRepository);
        this.systemService = systemService;
        this.requestService = requestService;
        this.brokerRepository = brokerRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    @Override
    public Client update(@NotNull final Long id,
                         @NotNull final Client client) {
        if (repository.existsById(id)) {
            if (client.getIsAuthenticated()) {
                client.setId(id);
                return repository.save(client);
            }
        }
        return null;
    }

    @Override
    public void deleteById(final Long id) {
        Client client = repository.findById(id).orElse(null);
        if (client != null) {
            Passport passport = client.getPassport();
            if (passport != null) {
                client.setPassport(null);
                repository.save(client);
                systemService.deletePassport(passport);
            }
            Credentials credentials = client.getCredentials();
            if (credentials != null) {
                client.setCredentials(null);
                repository.save(client);
                systemService.deleteCredentials(credentials);
            }
            repository.deleteById(id);
        }
    }

    public BrokerageAccount openBrokerageAccount(@NotNull final Map<String, Object> data) {
        BrokerageAccount brokerageAccount = null;
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final String currency = data.get(Constants.System.CURRENCY).toString();
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated() && client.getPassport() != null && client.getBrokerageAccount() == null) {
                brokerageAccount = new BrokerageAccount(-100L, currency);
                brokerageAccount.setClientPassportId(client.getPassport().getId());
                client.setBrokerageAccount(brokerageAccount);
                repository.save(client);
            }
        }
        return brokerageAccount;
    }

    public boolean closeBrokerageAccount(@NotNull final Long clientId) {
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated() && client.getAgreement() == null) {
                BrokerageAccount brokerageAccount = client.getBrokerageAccount();
                if (brokerageAccountRepository.existsById(brokerageAccount.getId())) {
                    brokerageAccountRepository.deleteById(brokerageAccount.getId());
                    client.setBrokerageAccount(null);
                    repository.save(client);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean putMoneyToAccount(@NotNull final Map<String, Object> data) {
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final Long brokerageAccountId = Long.parseLong(data.get("brokerageAccountId").toString());
        final long money = Long.parseLong(data.get("money").toString());

        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated()) {
                if (!brokerageAccountRepository.existsById(brokerageAccountId)) {
                    return false;
                }
                BrokerageAccount brokerageAccount = client.getBrokerageAccount();
                if (brokerageAccount.getId().equals(brokerageAccountId)) {
                    long currentBalance = brokerageAccount.getMoney();
                    brokerageAccount.setMoney(currentBalance + money);
                    client.setBrokerageAccount(brokerageAccount);
                    this.update(clientId, client);
                    return true;
                }
            }
        }
        return false;
    }

    public Agreement makeBrokerAgreement(@NotNull final Map<String, Object> data) {
        Agreement agreement = null;
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final String validity = data.get("validity").toString();
        final Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated()) {
                final Broker vacantBroker = brokerRepository.findAll().isEmpty()
                        ? null
                        : systemService.getVacantBroker(brokerRepository.findAll(), clientId);
                if (vacantBroker != null) {
                    // create agreement
                    agreement = new Agreement(clientId, vacantBroker.getId(), validity, Instant.now());
                    systemService.saveAgreement(agreement);
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
                }
            }
        }
        return agreement;
    }

    public Agreement extendBrokerAgreement(@NotNull final Map<String, Object> data) {
        Agreement newClientAgreement = null;
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final String newValidity = data.get("validity").toString();
        final Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            Agreement clientAgreement = client.getAgreement();
            if (clientAgreement != null) {
                clientAgreement.setStartDate(Instant.now());
                clientAgreement.setValidity(newValidity);
                systemService.saveAgreement(clientAgreement);
                newClientAgreement = clientAgreement;
            }
        }
        return newClientAgreement;
    }

    public boolean breakBrokerAgreement(@NotNull final Long clientId) {
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            Agreement agreement = client.getAgreement();
            if (client.getIsAuthenticated() && agreement != null) {
                // update broker
                final long brokerId = agreement.getBrokerId();
                Broker currentBroker = brokerRepository.findById(brokerId).orElse(null);
                if (currentBroker != null) {
                    List<Agreement> brokerAgreements = currentBroker.getAgreements();
                    brokerAgreements.remove(agreement);
                    currentBroker.setAgreements(brokerAgreements);
                    brokerRepository.save(currentBroker);
                    // update client
                    client.setAgreement(null);
                    repository.save(client);
                    // remove agreement
                    systemService.deleteAgreement(agreement);
                    return true;
                }
            }
        }
        return false;
    }

    public ClientRequest exchangeMoneyToStocks(@NotNull final Map<String, Object> data) {
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final Long moneyAmount = Long.parseLong(data.get("moneyAmount").toString());
        final String stockType = data.get("stockType").toString();

        ClientRequest clientRequest = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null && checkAgreement(clientId)) {
            final Long brokerId = client.getAgreement().getBrokerId();
            clientRequest = new ClientRequest(brokerId, clientId, moneyAmount, stockType, Constants.Exchange.MONEY_TO_STOCKS);
            requestService.saveClientRequest(clientRequest);
            List<ClientRequest> clientRequests = client.getRequests();
            clientRequests.add(clientRequest);
            client.setRequests(clientRequests);
            repository.save(client);
        }
        return clientRequest;
    }

    public ClientRequest exchangeStocksToMoney(@NotNull final Map<String, Object> data) {
        final Long clientId = Long.parseLong(data.get("clientId").toString());
        final Long stockAmount = Long.parseLong(data.get("stockAmount").toString());
        final String currency = data.get("currency").toString();

        ClientRequest clientRequest = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null && checkAgreement(clientId)) {
            final Long brokerId = client.getAgreement().getBrokerId();
            clientRequest = new ClientRequest(brokerId, clientId, stockAmount, currency, Constants.Exchange.STOCKS_TO_MONEY);
            requestService.saveClientRequest(clientRequest);
            List<ClientRequest> clientRequests = client.getRequests();
            clientRequests.add(clientRequest);
            client.setRequests(clientRequests);
            repository.save(client);
        }
        return clientRequest;
    }

    private boolean checkAgreement(@NotNull final Long clientId) {
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            Agreement clientAgreement = client.getAgreement();
            if (clientAgreement != null) {
                Instant startDate = clientAgreement.getStartDate();
                switch (clientAgreement.getValidity()) {
                    case Constants.Validity.YEAR:
                        if (startDate.plus(Constants.ValidityInts.YEAR, ChronoUnit.DAYS).isAfter(Instant.now())) {
                            return true;
                        }
                    case Constants.Validity.HALF_YEAR:
                        if (startDate.plus(Constants.ValidityInts.HALF_YEAR, ChronoUnit.DAYS).isAfter(Instant.now())) {
                            return true;
                        }
                    case Constants.Validity.MONTH:
                        if (startDate.plus(Constants.ValidityInts.MONTH, ChronoUnit.DAYS).isAfter(Instant.now())) {
                            return true;
                        }
                }
            }
        }
        return false;
    }
}
