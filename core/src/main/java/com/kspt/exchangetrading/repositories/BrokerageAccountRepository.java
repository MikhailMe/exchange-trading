package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrokerageAccountRepository extends JpaRepository<BrokerageAccount, Long> {

    @Override
    <S extends BrokerageAccount> S save (S entity);

    @Override
    List<BrokerageAccount> findAll();

}
