package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Broker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrokerRepository extends JpaRepository<Broker, Long> {

    @Override
    <S extends Broker> S save(S broker);
}
