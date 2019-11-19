package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.request.AdminRequest;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.repositories.requests.AdminRequestRepository;
import com.kspt.exchangetrading.repositories.requests.ClientRequestRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestService {

    private final AdminRequestRepository adminRequestRepository;


    private final ClientRequestRepository clientRequestRepository;

    public RequestService(@NotNull final AdminRequestRepository adminRequestRepository,
                          @NotNull final ClientRequestRepository clientRequestRepository) {
        this.adminRequestRepository = adminRequestRepository;
        this.clientRequestRepository = clientRequestRepository;
    }

    public AdminRequest saveAdminRequest(@NotNull final AdminRequest adminRequest) {
        return adminRequestRepository.save(adminRequest);
    }

    public ClientRequest saveClientRequest(@NotNull final ClientRequest clientRequest) {
        return clientRequestRepository.save(clientRequest);
    }

    public List<ClientRequest> getRequestsByBrokerId(@NotNull final Long brokerId) {
        return clientRequestRepository.findByTo(brokerId);
    }

    public List<ClientRequest> getApprovedRequests() {
        return clientRequestRepository.findByStatus(Constants.ClientRequestStatus.APPROVED_BY_BROKER);
    }

    public Optional<ClientRequest> getClientRequest(@NotNull final Long clientRequestId) {
        return clientRequestRepository.findById(clientRequestId);
    }
}
