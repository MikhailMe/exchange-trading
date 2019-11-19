package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Stock;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.services.AbstractService;
import com.kspt.exchangetrading.services.RequestService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BrokerService extends AbstractService<Broker, BrokerRepository> {

    private final RequestService requestService;

    private final ClientRepository clientRepository;

    public BrokerService(@NotNull final BrokerRepository repository,
                         @NotNull final RequestService requestService,
                         @NotNull final ClientRepository clientRepository) {
        super(repository);
        this.requestService = requestService;
        this.clientRepository = clientRepository;
    }

    public List<ClientRequest> checkRequests(@NotNull final Long brokerId) {
        Broker broker = repository.findById(brokerId).orElse(null);
        return broker != null && broker.getIsAuthenticated()
                ? requestService
                .getRequestsByBrokerId(brokerId)
                .stream()
                .filter(clientRequest -> clientRequest.getStatus().equals(Constants.ClientRequestStatus.PROCESSING))
                .collect(Collectors.toList())
                : null;
    }

    public boolean validateClientRequest(@NotNull final Map<String, Long> data) {
        final long brokerId = Long.parseLong(data.get("brokerId").toString());
        Broker broker = repository.findById(brokerId).orElse(null);
        if (broker != null && broker.getIsAuthenticated()) {
            final long clientRequestId = Long.parseLong(data.get("clientRequestId").toString());
            ClientRequest clientRequest = requestService.getClientRequest(clientRequestId).orElse(null);
            if (clientRequest != null) {
                Client client = clientRepository.findById(clientRequest.getFrom()).orElse(null);
                if (client != null && client.getBrokerageAccount() != null) {
                    switch (clientRequest.getRequestType()) {
                        case Constants.Exchange.MONEY_TO_STOCKS: {
                            if (client.getBrokerageAccount().getAsset().getQuantity() >= clientRequest.getAmount())
                                return true;
                        }
                        case Constants.Exchange.STOCKS_TO_MONEY: {
                            Set<Stock> clientStocks = client.getBrokerageAccount().getStocks();
                            for (Stock stock : clientStocks)
                                if (stock.getStockType().equals(clientRequest.getAssetType()))
                                    if (stock.getAmount() >= clientRequest.getAmount())
                                        return true;
                            break;
                        }
                    }
                }
            }
        }
        return false;
    }

    public String approveOrDeclineClientRequest(@NotNull final Map<String, Long> data,
                                                final boolean isApproved) {
        final long brokerId = Long.parseLong(data.get("brokerId").toString());
        final long clientRequestId = Long.parseLong(data.get("clientRequestId").toString());
        ClientRequest clientRequest = requestService.getClientRequest(clientRequestId).orElse(null);
        if (clientRequest != null) {
            if (isApproved) {
                clientRequest.setStatus(Constants.ClientRequestStatus.APPROVED_BY_BROKER);
                Broker broker = repository.findById(brokerId).orElse(null);
                if (broker != null && broker.getIsAuthenticated()) {
                    clientRequest.setAdminId(broker.getAdminId());
                } else {
                    return Constants.Status.FAILURE;
                }
            } else {
                clientRequest.setStatus(Constants.ClientRequestStatus.DECLINED);
            }
            ClientRequest savedClientRequest = requestService.saveClientRequest(clientRequest);
            if (savedClientRequest != null) {
                return Constants.Status.SUCCESS;
            }
        }
        return Constants.Status.FAILURE;
    }
}
