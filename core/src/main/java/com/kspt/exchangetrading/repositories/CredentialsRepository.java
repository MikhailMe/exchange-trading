package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.system.Credentials;
import org.jetbrains.annotations.NotNull;

public interface CredentialsRepository extends CommonRepository<Credentials> {

    Credentials findByLoginAndPassword(@NotNull final String login,
                                       @NotNull final String password);
}
