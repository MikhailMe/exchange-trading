package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.treasury.*;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.repositories.ClientRequestRepository;
import com.kspt.exchangetrading.repositories.actors.AdminRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.repositories.system.BrokerageAccountRepository;
import com.kspt.exchangetrading.services.CrudService;
import com.kspt.exchangetrading.services.TreasuryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService extends CrudService<Admin, AdminRepository> {

    private final TreasuryService treasuryService;
    private final ClientRepository clientRepository;
    private final ClientRequestRepository clientRequestRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public AdminService(@NotNull final AdminRepository repository,
                        @NotNull final TreasuryService treasuryService,
                        @NotNull final ClientRepository clientRepository,
                        @NotNull final ClientRequestRepository clientRequestRepository,
                        @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        super(repository);
        this.treasuryService = treasuryService;
        this.clientRepository = clientRepository;
        this.clientRequestRepository = clientRequestRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
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

    public String declineRequest(@NotNull final Long clientRequestId) {
        ClientRequest clientRequest = clientRequestRepository.findById(clientRequestId).orElse(null);
        if (clientRequest != null) {
            clientRequest.setStatus(Constants.ClientRequestStatus.DECLINED);
            clientRequestRepository.save(clientRequest);
            return Constants.Status.SUCCESS;
        }
        return Constants.Status.FAILURE;
    }

    @Transactional
    public Transaction approveRequest(@NotNull final Map<String, String> data) {
        final long adminId = Long.parseLong(data.get("adminId"));
        final long clientRequestId = Long.parseLong(data.get("clientRequestId"));

        // TODO: moved logic to generate transaction and get info from transaction
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
                BrokerageAccount newBrokerageAccount = null;
                switch (clientRequest.getRequestType()) {
                    case Constants.Exchange.MONEY_TO_STOCKS: {
                        newBrokerageAccount = transfer(
                                transaction,
                                client.getBrokerageAccount(),
                                transaction.getAsset().getQuantity(),
                                treasuryService::exchangeMoneyToStocks);
                        break;
                    }
                    case Constants.Exchange.STOCKS_TO_MONEY: {
                        newBrokerageAccount = transfer(
                                transaction,
                                client.getBrokerageAccount(),
                                transaction.getStock().getQuantity(),
                                treasuryService::exchangeStocksToMoney);
                        break;
                    }
                }
                if (newBrokerageAccount != null) {
                    List<Long> transactions = client.getTransactions();
                    transactions.add(transaction.getId());
                    client.setTransactions(transactions);
                    client.setBrokerageAccount(newBrokerageAccount);
                    clientRepository.save(client);
                }
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

    @NotNull
    private Transaction generateTransaction(@NotNull final Long adminId,
                                            @NotNull final ClientRequest clientRequest) {
        Transaction transaction;
        final Long clientId = clientRequest.getClientId();
        transaction = new Transaction(adminId, clientId, clientRequest.getRequestType());
        switch (clientRequest.getRequestType()) {
            case Constants.Exchange.MONEY_TO_STOCKS: {
                final Double moneyQuantity = clientRequest.getQuantity();
                final String currency = clientRequest.getFromType();
                final Asset transactionAsset = new Asset(clientId, currency, moneyQuantity);
                transaction.setAsset(transactionAsset);
                final Stock transactionStock = new Stock(clientId, clientRequest.getToType(), 0d);
                transaction.setStock(transactionStock);
                return transaction;
            }
            case Constants.Exchange.STOCKS_TO_MONEY: {
                final String stockType = clientRequest.getFromType();
                final Double stockQuantity = clientRequest.getQuantity();
                final Stock transactionStock = new Stock(clientId, stockType, stockQuantity);
                transaction.setStock(transactionStock);
                final Asset transactionAsset = new Asset(clientId, clientRequest.getToType(), 0d);
                transaction.setAsset(transactionAsset);
                return transaction;
            }
        }
        return transaction;
    }

    private BrokerageAccount transfer(@NotNull final Transaction transaction,
                                      @NotNull final BrokerageAccount brokerageAccount,
                                      @NotNull final Double quantity,
                                      @NotNull final Quantitable<Asset, Stock, Double> getQuantity) {
        // update assets
        List<Asset> assets = brokerageAccount.getAssets();
        Asset asset = assets
                .stream()
                .filter(x -> x.getType().equals(transaction.getAsset().getType()))
                .findFirst().orElse(null);
        if (asset != null) {
            assets.remove(asset);
            treasuryService.assetRepository.delete(asset);
            final Double currentBalance = asset.getQuantity();
            final Double newCurrentBalance = currentBalance - quantity;
            asset.setQuantity(newCurrentBalance);
            Asset savedAsset = treasuryService
                    .assetRepository
                    .save(new Asset(asset.getClientId(), asset.getType(), newCurrentBalance));
            assets.add(savedAsset);
            brokerageAccount.setAssets(assets);
        }

        // update stocks
        List<Stock> stocks = brokerageAccount.getStocks();
        Stock stock = stocks
                .stream()
                .filter(x -> x.getStockType().equals(transaction.getStock().getStockType()))
                .findFirst().orElse(null);
        if (stock != null) {
            stocks.remove(stock);
            final Double currentBalance = stock.getQuantity();
            final Double gotFromTransfer = getQuantity.apply(transaction.getAsset(), stock);
            final Double newCurrentBalance = currentBalance + gotFromTransfer;
            stock.setQuantity(newCurrentBalance);
            treasuryService.stockRepository.save(stock);
            stocks.add(stock);
        } else {
            Stock newStock = new Stock(transaction.getClientId(), transaction.getStock().getStockType(), 0d);
            final Double newBalance = getQuantity.apply(transaction.getAsset(), newStock);
            newStock.setQuantity(newBalance);
            Stock savedStock = treasuryService.stockRepository.save(newStock);
            stocks.add(savedStock);
        }
        brokerageAccount.setStocks(stocks);

        // update brokerageAccount table
        brokerageAccountRepository.save(brokerageAccount);
        return brokerageAccount;
    }

    @FunctionalInterface
    private interface Quantitable<F, S, R> {
        R apply(F f, S s);
    }

}
