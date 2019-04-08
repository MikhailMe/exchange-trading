package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.system.Agreement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgreementRepository extends JpaRepository<Agreement, Long> {

}
