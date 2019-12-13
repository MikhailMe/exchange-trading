package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.ClientRequest;
import com.kspt.exchangetrading.models.treasury.*;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.repositories.ClientRequestRepository;
import com.kspt.exchangetrading.repositories.actors.AdminRepository;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.services.CrudService;
import com.kspt.exchangetrading.services.TreasuryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService extends CrudService<Admin, AdminRepository> {

    private final TreasuryService treasuryService;
    private final BrokerRepository brokerRepository;
    private final ClientRepository clientRepository;
    private final ClientRequestRepository clientRequestRepository;

    public AdminService(@NotNull final AdminRepository repository,
                        @NotNull final TreasuryService treasuryService,
                        @NotNull final BrokerRepository brokerRepository,
                        @NotNull final ClientRepository clientRepository,
                        @NotNull final ClientRequestRepository clientRequestRepository) {
        super(repository);
        this.treasuryService = treasuryService;
        this.brokerRepository = brokerRepository;
        this.clientRepository = clientRepository;
        this.clientRequestRepository = clientRequestRepository;
    }

    public List<ClientRequest> checkApprovedByBrokerRequests(@NotNull final Long adminId) {
        Admin admin = repository.findById(adminId).orElse(null);
        return admin != null && admin.getIsAuthenticated()
                ? clientRequestRepository
                .findByStatus(Constants.ClientRequestStatus.APPROVED_BY_BROKER)
                .stream()
                .filter(clientRequest -> admin.getBrokers().contains(clientRequest.getBrokerId()))
                .filter(clientRequest -> clientRequest.getAdminId().equals(adminId))
                .collect(Collectors.toList())
                : null;
    }

    public void declineRequest(@NotNull final Long clientRequestId) {
        ClientRequest clientRequest = clientRequestRepository.findById(clientRequestId).orElse(null);
        if (clientRequest != null) {
            clientRequest.setStatus(Constants.ClientRequestStatus.DECLINED);
            clientRequestRepository.save(clientRequest);
        }
    }

    @Transactional
    public Transaction approveRequest(@NotNull final Long adminId,
                                      @NotNull final Map<String, String> data) {
        final long clientRequestId = Long.parseLong(data.get("clientRequestId"));

        Transaction transaction = null;

        ClientRequest clientRequest = clientRequestRepository.findById(clientRequestId).orElse(null);
        if (clientRequest != null) {

            // generate transaction for client
            transaction = generateTransaction(adminId, clientRequest);
            transaction = treasuryService.transactionRepository.save(transaction);

            // update client request
            clientRequest.setStatus(Constants.ClientRequestStatus.COMPLETED);
            clientRequestRepository.save(clientRequest);

            // update client
            Client client = clientRepository.findById(clientRequest.getClientId()).orElse(null);
            if (client != null) {
                transfer(transaction, client.getBrokerageAccount());
                final List<Long> transactions = client.getTransactions();
                transactions.add(transaction.getId());
                client.setTransactions(transactions);
                clientRepository.save(client);
            }

            Admin admin = repository.findById(adminId).orElse(null);
            if (admin != null) {
                List<Transaction> transactions = admin.getTransactions();
                transactions.add(transaction);
                admin.setTransactions(transactions);
                repository.save(admin);
            }

            treasuryService.bankTransfer(clientRequest.getRequestType(), transaction);
        }
        return transaction;
    }

    public List<Rate> getRates() {
        return treasuryService.getRates();
    }

    public List<BankRecord> getBankMoney() {
        return treasuryService.getBankMoney();
    }

    public List<Broker> getBrokers(@NotNull final Long adminId) {
        List<Broker> brokers = new ArrayList<>();
        repository.findById(adminId).ifPresent(admin -> admin.getBrokers().forEach(brokerId -> {
            brokerRepository.findById(brokerId).ifPresent(brokers::add);
        }));
        return brokers;
    }

    @NotNull
    private Transaction generateTransaction(@NotNull final Long adminId,
                                            @NotNull final ClientRequest clientRequest) {
        final Long clientId = clientRequest.getClientId();
        Transaction transaction = new Transaction(adminId, clientId, clientRequest.getRequestType());
        switch (clientRequest.getRequestType()) {
            case Constants.Exchange.MONEY_TO_STOCKS: {
                final Double moneyQuantity = clientRequest.getQuantity();
                final String currency = clientRequest.getFromType();
                final Asset transactionAsset = new Asset(clientId, currency, -moneyQuantity);
                transaction.setAsset(transactionAsset);
                final Stock transactionStock = new Stock(clientId, clientRequest.getToType());
                final Double transferStockQuantity = treasuryService.exchangeMoneyToStocks(transactionAsset, transactionStock);
                transactionStock.setQuantity(transferStockQuantity);
                transaction.setStock(transactionStock);
                return transaction;
            }
            case Constants.Exchange.STOCKS_TO_MONEY: {
                final String stockType = clientRequest.getFromType();
                final Double stockQuantity = clientRequest.getQuantity();
                final Stock transactionStock = new Stock(clientId, stockType, -stockQuantity);
                transaction.setStock(transactionStock);
                final Asset transactionAsset = new Asset(clientId, clientRequest.getToType());
                final Double transferAssetQuantity = treasuryService.exchangeStocksToMoney(transactionAsset, transactionStock);
                transactionAsset.setQuantity(transferAssetQuantity);
                transaction.setAsset(transactionAsset);
                return transaction;
            }
        }
        return transaction;
    }

    private void transfer(@NotNull final Transaction transaction,
                          @NotNull final BrokerageAccount brokerageAccount) {
        final Asset transactionAsset = transaction.getAsset();
        final Stock transactionStock = transaction.getStock();

        // update assets
        List<Asset> assets = brokerageAccount.getAssets();
        Asset asset = assets
                .stream()
                .filter(x -> x.getType().equals(transactionAsset.getType()))
                .findFirst().orElse(null);
        if (asset != null) {
            final Double currentBalance = asset.getQuantity();
            final Double newCurrentBalance = currentBalance + transactionAsset.getQuantity();
            asset.setQuantity(newCurrentBalance);
        } else {
            final Asset newAsset = new Asset(
                    transaction.getClientId(),
                    transaction.getAsset().getType(),
                    transaction.getAsset().getQuantity()
            );
            assets.add(treasuryService.assetRepository.save(newAsset));
        }
        brokerageAccount.setAssets(assets);

        // update stocks
        List<Stock> stocks = brokerageAccount.getStocks();
        Stock stock = stocks
                .stream()
                .filter(x -> x.getStockType().equals(transactionStock.getStockType()))
                .findFirst().orElse(null);
        if (stock != null) {
            final Double currentBalance = stock.getQuantity();
            final Double newCurrentBalance = currentBalance + transactionStock.getQuantity();
            stock.setQuantity(newCurrentBalance);
        } else {
            final Stock newStock = new Stock(
                    transaction.getClientId(),
                    transaction.getStock().getStockType(),
                    transactionStock.getQuantity());
            stocks.add(treasuryService.stockRepository.save(newStock));
        }
        brokerageAccount.setStocks(stocks);
    }
}
