package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.AdminRequest;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Asset;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Stock;
import com.kspt.exchangetrading.models.system.Transaction;
import com.kspt.exchangetrading.repositories.actors.AdminRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.services.AbstractService;
import com.kspt.exchangetrading.services.RequestService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService extends AbstractService<Admin, AdminRepository> {

    private final RequestService requestService;

    private final ClientRepository clientRepository;

    public AdminService(@NotNull final AdminRepository repository,
                        @NotNull final RequestService requestService,
                        @NotNull final ClientRepository clientRepository) {
        super(repository);
        this.requestService = requestService;
        this.clientRepository = clientRepository;
    }

    public List<ClientRequest> checkApprovedByBrokerRequests(@NotNull final Long adminId) {
        Admin admin = repository.findById(adminId).orElse(null);
        return admin != null && admin.getIsAuthenticated()
                ? requestService
                .getApprovedRequests()
                .stream()
                .filter(clientRequest -> admin.getBrokers().contains(clientRequest.getTo()))
                .filter(clientRequest -> clientRequest.getAdminId().equals(adminId))
                .collect(Collectors.toList())
                : null;
    }

    public AdminRequest approveRequest(@NotNull final Map<String, String> data) {
        final long adminId = Long.parseLong(data.get("adminId"));
        final long clientRequestId = Long.parseLong(data.get("clientRequestId"));

        AdminRequest adminRequest = null;

        Transaction transaction = generateTransaction(clientRequestId);

        ClientRequest clientRequest = requestService.getClientRequest(clientRequestId).orElse(null);
        if (clientRequest != null) {
            // update client request
            clientRequest.setStatus(Constants.ClientRequestStatus.COMPLETED);
            requestService.saveClientRequest(clientRequest);
            // update client
            Client client = clientRepository.findById(clientRequest.getFrom()).orElse(null);
            if (client != null) {
                adminRequest = makeTransferAsset(client, clientRequest);
            }
        }

        return adminRequest;
    }

    public String declineRequest(@NotNull final Map<String, String> data) {
        throw new NotImplementedException();
    }

    private AdminRequest makeTransferAsset(@NotNull final Client client,
                                           @NotNull final ClientRequest clientRequest) {
        BrokerageAccount brokerageAccount = client.getBrokerageAccount();
        AdminRequest adminRequest = null;
        switch (clientRequest.getRequestType()) {
            case Constants.Exchange.MONEY_TO_STOCKS:
                if (brokerageAccount != null) {

                }
                break;
            case Constants.Exchange.STOCKS_TO_MONEY:
                break;
        }
        return adminRequest;
    }

    private void transferMoneyToStocks(@NotNull final ClientRequest clientRequest,
                                       @NotNull final BrokerageAccount brokerageAccount) {
        // asset -> stockType, moneyAmount

        // update assets
        List<Asset> assets = brokerageAccount.getAssets();

        assets.add(clientRequest.getAsset());
        brokerageAccount.setAssets(assets);
        // update stocks
        List<Stock> stocks = brokerageAccount.getStocks();
        assets.add(clientRequest.get());
        brokerageAccount.setAssets(assets);
    }

    private void transferStocksToMoney(@NotNull final ClientRequest clientRequest,
                                       @NotNull final BrokerageAccount brokerageAccount) {
        // asset -> currency, stockAmount
        if (clientRequest.get)
    }

}
