package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.ClientRequest;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRequestRepository extends CommonRepository<ClientRequest> {

    List<ClientRequest> findByBrokerId(@NotNull final Long id);

    List<ClientRequest> findByStatus(@NotNull final String status);

}
