package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.system.Credentials;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface AdminRepository extends CommonRepository<Admin> {

    Optional<Admin> findByCredentials(@NotNull final Credentials credentials);
}
