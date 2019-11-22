package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.Rate;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RateRepository extends CommonRepository<Rate> {
}
