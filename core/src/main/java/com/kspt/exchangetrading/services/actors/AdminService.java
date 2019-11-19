package com.kspt.exchangetrading.services.actors;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.request.AdminRequest;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.repositories.actors.AdminRepository;
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

    public AdminService(@NotNull final AdminRepository repository,
                        @NotNull final RequestService requestService) {
        super(repository);
        this.requestService = requestService;
    }

    public List<ClientRequest> checkApprovedByBrokerRequests(@NotNull final Long adminId) {
        Admin admin = repository.findById(adminId).orElse(null);
        return admin != null && admin.getIsAuthenticated()
                ? requestService
                .getApprovedRequests()
                .stream()
                .filter(clientRequest -> admin.getBrokers()
                        .stream()
                        .map(AbstractEntity::getId)
                        .collect(Collectors.toList())
                        .contains(clientRequest.getTo()))
                .filter(clientRequest -> clientRequest.getAdminId().equals(adminId))
                .collect(Collectors.toList())
                : null;
    }

    public AdminRequest approveRequest(@NotNull final Map<String, String> data) {
        throw new NotImplementedException();
    }

    public String declineRequest(@NotNull final Map<String, String> data) {
        throw new NotImplementedException();
    }

}
