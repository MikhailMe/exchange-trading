package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.ClientRequest;
import com.kspt.exchangetrading.models.system.*;
import com.kspt.exchangetrading.models.treasury.Asset;
import com.kspt.exchangetrading.models.treasury.Stock;
import com.kspt.exchangetrading.models.treasury.Transaction;
import com.kspt.exchangetrading.repositories.ClientRequestRepository;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.repositories.system.AgreementRepository;
import com.kspt.exchangetrading.repositories.system.BrokerageAccountRepository;
import com.kspt.exchangetrading.repositories.system.PassportRepository;
import com.kspt.exchangetrading.repositories.treasury.AssetRepository;
import com.kspt.exchangetrading.repositories.treasury.StockRepository;
import com.kspt.exchangetrading.repositories.treasury.TransactionRepository;
import com.kspt.exchangetrading.services.CrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService extends CrudService<Client, ClientRepository> {

    private final AssetRepository assetRepository;
    private final StockRepository stockRepository;
    private final BrokerRepository brokerRepository;
    private final PassportRepository passportRepository;
    private final AgreementRepository agreementRepository;
    private final TransactionRepository transactionRepository;
    private final ClientRequestRepository clientRequestRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public ClientService(@NotNull final AssetRepository assetRepository,
                         @NotNull final StockRepository stockRepository,
                         @NotNull final BrokerRepository brokerRepository,
                         @NotNull final ClientRepository clientRepository,
                         @NotNull final PassportRepository passportRepository,
                         @NotNull final AgreementRepository agreementRepository,
                         @NotNull final TransactionRepository transactionRepository,
                         @NotNull final ClientRequestRepository clientRequestRepository,
                         @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        super(clientRepository);
        this.assetRepository = assetRepository;
        this.stockRepository = stockRepository;
        this.brokerRepository = brokerRepository;
        this.passportRepository = passportRepository;
        this.agreementRepository = agreementRepository;
        this.transactionRepository = transactionRepository;
        this.clientRequestRepository = clientRequestRepository;
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

    public Client setPassport(@NotNull final Long clientId,
                              @NotNull final Map<String, String> data) {
        final int series = Integer.parseInt(data.get("series"));
        final int number = Integer.parseInt(data.get("number"));
        final Passport passport = passportRepository.save(new Passport(series, number));
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            client.setPassport(passport);
            repository.save(client);
        }
        return client;
    }

    public BrokerageAccount openBrokerageAccount(@NotNull final Long clientId) {
        BrokerageAccount brokerageAccount = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated() && client.getPassport() != null && client.getBrokerageAccount() == null) {
                brokerageAccount = new BrokerageAccount();
                List<Asset> assets = brokerageAccount.getAssets();
                if (assets != null) {
                    Asset savedAsset = assetRepository.save(new Asset(client.getId(), Constants.Currency.RUBLE, -100d));
                    assets.add(savedAsset);
                    brokerageAccount.setAssets(assets);
                    brokerageAccount.setClientPassportId(client.getPassport().getId());
                    client.setBrokerageAccount(brokerageAccount);
                    brokerageAccount = repository.save(client).getBrokerageAccount();
                }
            }
        }
        return brokerageAccount;
    }

    @Transactional
    public boolean closeBrokerageAccount(@NotNull final Long clientId) {
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated() && client.getAgreement() == null) {
                BrokerageAccount brokerageAccount = client.getBrokerageAccount();
                if (brokerageAccountRepository.existsById(brokerageAccount.getId())) {
                    client.setBrokerageAccount(null);
                    repository.save(client);
                    brokerageAccountRepository.delete(brokerageAccount);
                    for (Asset asset: brokerageAccount.getAssets()) assetRepository.delete(asset);
                    brokerageAccount.setAssets(null);
                    for (Stock stock: brokerageAccount.getStocks()) stockRepository.delete(stock);
                    brokerageAccount.setStocks(null);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean putMoneyToAccount(@NotNull final Long clientId,
                                     @NotNull final Map<String, String> data) {
        final double money = Double.parseDouble(data.get("money"));
        final String currency = data.get("currency");

        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated() && client.getBrokerageAccount() != null) {
                BrokerageAccount brokerageAccount = client.getBrokerageAccount();
                final long brokerageAccountId = brokerageAccount.getId();
                if (!brokerageAccountRepository.existsById(brokerageAccountId)) {
                    return false;
                }
                List<Asset> assets = brokerageAccount.getAssets();
                if (assets != null) {
                    Asset asset = assets.stream().filter(x -> x.getType().equals(currency)).findFirst().orElse(null);
                    if (asset != null) {
                        assets.remove(asset);
                        Double currentBalance = asset.getQuantity();
                        asset.setQuantity(currentBalance + money);
                        assets.add(asset);
                    } else {
                        Asset newAsset = assetRepository.save(new Asset(clientId, currency, money));
                        assets.add(newAsset);
                    }
                    brokerageAccount.setAssets(assets);
                    client.setBrokerageAccount(brokerageAccount);
                    this.update(clientId, client);
                    return true;
                }
            }
        }
        return false;
    }

    public Agreement makeBrokerAgreement(@NotNull final Long clientId,
                                         @NotNull final Map<String, Object> data) {
        Agreement agreement = null;
        final String validity = data.get("validity").toString();
        final Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            if (client.getIsAuthenticated()) {
                final Broker vacantBroker = brokerRepository.findAll().isEmpty()
                        ? null
                        : getVacantBroker(brokerRepository.findAll(), clientId);
                if (vacantBroker != null) {
                    // create agreement
                    agreement = new Agreement(clientId, vacantBroker.getId(), validity, Instant.now());
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
                }
            }
        }
        return agreement;
    }

    public Agreement extendBrokerAgreement(@NotNull final Long clientId,
                                           @NotNull final Map<String, Object> data) {
        Agreement newClientAgreement = null;
        final String newValidity = data.get("validity").toString();
        final Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            Agreement clientAgreement = client.getAgreement();
            if (clientAgreement != null) {
                clientAgreement.setStartDate(Instant.now());
                clientAgreement.setValidity(newValidity);
                agreementRepository.save(clientAgreement);
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
                    agreementRepository.delete(agreement);
                    return true;
                }
            }
        }
        return false;
    }

    public ClientRequest exchange(@NotNull final Long clientId,
                                  @NotNull final Map<String, Object> data,
                                  @NotNull final String requestType) {
        final Double quantity = Double.parseDouble(data.get("quantity").toString());
        final String fromType = data.get("fromType").toString();
        final String toType = data.get("toType").toString();

        ClientRequest clientRequest = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null && checkAgreement(clientId)) {
            final Long brokerId = client.getAgreement().getBrokerId();
            clientRequest = new ClientRequest(
                    brokerId, clientId,
                    fromType, toType,
                    quantity, requestType);
            clientRequest = clientRequestRepository.save(clientRequest);
            List<Long> clientRequests = client.getRequests();
            clientRequests.add(clientRequest.getId());
            client.setRequests(clientRequests);
            repository.save(client);
        }
        return clientRequest;
    }

    public List<Transaction> getTransactions(@NotNull final Long clientId) {
        return transactionRepository.findByClientId(clientId);
    }

    public Transaction getTransactionById(@NotNull final Long clientId,
                                          @NotNull final Long transactionId) {
        Transaction transaction = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            transaction = transactionRepository.findByIdAndClientId(transactionId, clientId);
        }
        return transaction;
    }

    public List<ClientRequest> getRequests(@NotNull final Long clientId) {
        return clientRequestRepository.findByClientId(clientId);
    }

    public ClientRequest getRequestById(@NotNull final Long clientId,
                                        @NotNull final Long requestId) {
        ClientRequest clientRequest = null;
        Client client = repository.findById(clientId).orElse(null);
        if (client != null) {
            clientRequest = clientRequestRepository.findByIdAndClientId(requestId, clientId);
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

    private Broker getVacantBroker(@NotNull final List<Broker> brokers,
                                   @NotNull final Long clientId) {
        Broker minBroker = brokers.get(0);
        int brokerAgreementsSize = Integer.MAX_VALUE;
        for (Broker broker : brokers) {
            if (broker.getAgreements() != null && broker.getAgreements().size() < brokerAgreementsSize) {
                brokerAgreementsSize = broker.getAgreements().size();
                minBroker = broker;
            }
        }
        if (!minBroker.getAgreements()
                .stream()
                .map(Agreement::getClientId)
                .collect(Collectors.toList())
                .contains(clientId)) {
            return minBroker;
        } else {
            brokers.remove(minBroker);
            return getVacantBroker(brokers, clientId);
        }
    }
}
