package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.repositories.AgreementRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
public class AgreementService extends AbstractService<Agreement, AgreementRepository> {

    public AgreementService(@NotNull AgreementRepository repository) {
        super(repository);
    }
}
