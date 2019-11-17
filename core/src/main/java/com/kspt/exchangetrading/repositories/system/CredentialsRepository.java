package com.kspt.exchangetrading.repositories.system;

import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CredentialsRepository extends CommonRepository<Credentials> {

    Optional<Credentials> findByLoginAndPassword(@NotNull final String login,
                                                 @NotNull final String password);

    @Override
    void delete(@NotNull final Credentials credentials);
}
