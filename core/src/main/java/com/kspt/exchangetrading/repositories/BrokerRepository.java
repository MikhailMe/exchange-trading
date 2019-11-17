package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.system.Credentials;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BrokerRepository extends CommonRepository<Broker> {

    Optional<Broker> findByCredentials(@NotNull final Credentials credentials);

}
