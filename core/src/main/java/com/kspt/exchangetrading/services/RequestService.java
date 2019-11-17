package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.request.AdminRequest;
import com.kspt.exchangetrading.models.request.BrokerRequest;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.repositories.requests.AdminRequestRepository;
import com.kspt.exchangetrading.repositories.requests.BrokerRequestRepository;
import com.kspt.exchangetrading.repositories.requests.ClientRequestRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class RequestService {

    private final AdminRequestRepository adminRequestRepository;

    private final BrokerRequestRepository brokerRequestRepository;

    private final ClientRequestRepository clientRequestRepository;

    public RequestService(@NotNull final AdminRequestRepository adminRequestRepository,
                          @NotNull final BrokerRequestRepository brokerRequestRepository,
                          @NotNull final ClientRequestRepository clientRequestRepository) {
        this.adminRequestRepository = adminRequestRepository;
        this.brokerRequestRepository = brokerRequestRepository;
        this.clientRequestRepository = clientRequestRepository;
    }

    public AdminRequest saveAdminRequest(@NotNull final AdminRequest adminRequest) {
        return adminRequestRepository.save(adminRequest);
    }

    public BrokerRequest saveBrokerRequest(@NotNull final BrokerRequest brokerRequest) {
        return brokerRequestRepository.save(brokerRequest);
    }

    public ClientRequest saveClientRequest(@NotNull final ClientRequest clientRequest) {
        return clientRequestRepository.save(clientRequest);
    }
}
