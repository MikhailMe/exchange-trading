package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Stock;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockRepository extends CommonRepository<Stock> {
}
