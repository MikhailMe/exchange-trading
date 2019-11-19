package com.kspt.exchangetrading.repositories.requests;

import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRequestRepository extends CommonRepository<ClientRequest> {

    List<ClientRequest> findByTo(@NotNull final Long id);

    List<ClientRequest> findByStatus(@NotNull final String status);

}
