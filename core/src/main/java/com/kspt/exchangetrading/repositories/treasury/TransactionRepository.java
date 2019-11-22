package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Transaction;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CommonRepository<Transaction> {

    List<Transaction> findByClientId(@NotNull final Long id);
}
