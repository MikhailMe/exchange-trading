package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Stock;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockRepository extends CommonRepository<Stock> {

    List<Stock> findByClientId(@NotNull final Long clientId);

    Stock findByIdAndClientId(@NotNull final Long id, @NotNull final Long clientId);
}
