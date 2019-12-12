package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.ClientRequest;
import com.kspt.exchangetrading.models.treasury.Asset;
import com.kspt.exchangetrading.models.treasury.Stock;
import com.kspt.exchangetrading.repositories.ClientRequestRepository;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.services.CrudService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BrokerService extends CrudService<Broker, BrokerRepository> {

    private final ClientRepository clientRepository;
    private final ClientRequestRepository clientRequestRepository;

    public BrokerService(@NotNull final BrokerRepository repository,
                         @NotNull final ClientRequestRepository clientRequestRepository,
                         @NotNull final ClientRepository clientRepository) {
        super(repository);
        this.clientRequestRepository = clientRequestRepository;
        this.clientRepository = clientRepository;
    }

    public List<ClientRequest> checkRequests(@NotNull final Long brokerId) {
        Broker broker = repository.findById(brokerId).orElse(null);
        return broker != null && broker.getIsAuthenticated()
                ? clientRequestRepository
                .findByBrokerId(brokerId)
                .stream()
                .filter(clientRequest -> clientRequest.getStatus().equals(Constants.ClientRequestStatus.PROCESSING))
                .collect(Collectors.toList())
                : null;
    }

    public boolean validateClientRequest(@NotNull final Long brokerId,
                                         @NotNull final Map<String, Long> data) {
        Broker broker = repository.findById(brokerId).orElse(null);
        if (broker != null && broker.getIsAuthenticated()) {
            final long clientRequestId = Long.parseLong(data.get("clientRequestId").toString());
            ClientRequest clientRequest = clientRequestRepository.findById(clientRequestId).orElse(null);
            if (clientRequest != null) {
                Client client = clientRepository.findById(clientRequest.getClientId()).orElse(null);
                if (client != null && client.getBrokerageAccount() != null) {
                    switch (clientRequest.getRequestType()) {
                        case Constants.Exchange.MONEY_TO_STOCKS: {
                            for (Asset asset : client.getBrokerageAccount().getAssets())
                                if (asset.getType().equals(clientRequest.getFromType())
                                        && asset.getQuantity() >= clientRequest.getQuantity())
                                    return true;
                        }
                        case Constants.Exchange.STOCKS_TO_MONEY: {
                            for (Stock stock : client.getBrokerageAccount().getStocks())
                                if (stock.getStockType().equals(clientRequest.getFromType())
                                        && stock.getQuantity() >= clientRequest.getQuantity())
                                    return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void approveOrDeclineClientRequest(@NotNull final Long brokerId,
                                              @NotNull final Map<String, Long> data,
                                              final boolean isApproved) {
        final long clientRequestId = Long.parseLong(data.get("clientRequestId").toString());
        ClientRequest clientRequest = clientRequestRepository.findById(clientRequestId).orElse(null);
        if (clientRequest != null) {
            if (isApproved) {
                clientRequest.setStatus(Constants.ClientRequestStatus.APPROVED_BY_BROKER);
                Broker broker = repository.findById(brokerId).orElse(null);
                if (broker != null && broker.getIsAuthenticated()) {
                    clientRequest.setAdminId(broker.getAdminId());
                } else {
                    return;
                }
            } else {
                clientRequest.setStatus(Constants.ClientRequestStatus.DECLINED);
            }
            clientRequestRepository.save(clientRequest);
        }
    }
}
