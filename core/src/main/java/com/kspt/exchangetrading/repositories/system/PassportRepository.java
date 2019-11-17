package com.kspt.exchangetrading.repositories.system;

import com.kspt.exchangetrading.models.system.Passport;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface PassportRepository extends CommonRepository<Passport> {

    @Override
    void delete(@NotNull final Passport passport);
}
