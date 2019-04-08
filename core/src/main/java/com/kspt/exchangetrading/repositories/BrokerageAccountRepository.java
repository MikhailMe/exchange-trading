package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.system.BrokerageAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrokerageAccountRepository extends JpaRepository<BrokerageAccount, Long> {

    Optional<BrokerageAccount> getByNumber(long number);

    @Override
    <S extends BrokerageAccount> S save(S brokerageAccount);

}
