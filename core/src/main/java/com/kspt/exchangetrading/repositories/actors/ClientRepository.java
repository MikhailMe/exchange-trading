package com.kspt.exchangetrading.repositories.actors;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.models.system.Passport;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends CommonRepository<Client> {

    Optional<Client> findByPassport(@NotNull final Passport passport);

    Optional<Client> findByAgreement(@NotNull final Agreement agreement);

    Optional<Client> findByCredentials(@NotNull final Credentials credentials);
}
