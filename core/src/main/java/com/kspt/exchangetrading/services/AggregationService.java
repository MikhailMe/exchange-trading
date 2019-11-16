package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.repositories.AgreementRepository;
import com.kspt.exchangetrading.repositories.BrokerRepository;
import com.kspt.exchangetrading.repositories.BrokerageAccountRepository;
import com.kspt.exchangetrading.repositories.ClientRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AggregationService {

    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;
    private final AgreementRepository agreementRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public AggregationService(@NotNull final ClientRepository clientRepository,
                              @NotNull final BrokerRepository brokerRepository,
                              @NotNull final AgreementRepository agreementRepository,
                              @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        this.clientRepository = clientRepository;
        this.brokerRepository = brokerRepository;
        this.agreementRepository = agreementRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    Broker getVacantBroker(final Long clientId) {
        List<Broker> brokers = brokerRepository.findAll();
        Broker vacantBroker = null;
        for (Broker broker : brokers) {
            List<Agreement> brokerAgreements = broker.getAgreements();
            if (brokerAgreements == null || brokerAgreements.isEmpty()) {
                vacantBroker = broker;
                break;
            } else {
                if (!brokerAgreements
                        .stream()
                        .map(Agreement::getClientId)
                        .collect(Collectors.toList())
                        .contains(clientId)) {
                    vacantBroker = broker;
                    break;
                }
            }
        }
        return vacantBroker;
    }

}
