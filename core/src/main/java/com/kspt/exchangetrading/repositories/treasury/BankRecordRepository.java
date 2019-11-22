package com.kspt.exchangetrading.repositories.treasury;

import com.kspt.exchangetrading.models.treasury.BankRecord;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankRecordRepository extends CommonRepository<BankRecord> {
}
