package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.system.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {

    @Override
    <S extends Passport> S save(S passport);
}
