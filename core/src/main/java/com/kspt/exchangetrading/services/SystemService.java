package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.actors.Person;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.parser.Parser;
import com.kspt.exchangetrading.repositories.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SystemService {

    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;
    private final AgreementRepository agreementRepository;
    private final CredentialsRepository credentialsRepository;
    private final BrokerageAccountRepository brokerageAccountRepository;

    public SystemService(@NotNull final AdminRepository adminRepository,
                         @NotNull final ClientRepository clientRepository,
                         @NotNull final BrokerRepository brokerRepository,
                         @NotNull final AgreementRepository agreementRepository,
                         @NotNull final CredentialsRepository credentialsRepository,
                         @NotNull final BrokerageAccountRepository brokerageAccountRepository) {
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.brokerRepository = brokerRepository;
        this.agreementRepository = agreementRepository;
        this.credentialsRepository = credentialsRepository;
        this.brokerageAccountRepository = brokerageAccountRepository;
    }

    public Person signUp(@NotNull final Map<String, Object> data) {
        final String login = data.get("login").toString();
        final String password = data.get("password").toString();
        final Credentials credentials = new Credentials(login, password);

        final String personType = data.get("personType").toString();
        final String name = data.get("name").toString();
        final String surname = data.get("surname").toString();

        switch (personType) {
            case Constants.CLIENT:
                Client client = new Client(name, surname, personType);
                client.setCredentials(credentials);
                return clientRepository.save(client);
            case Constants.BROKER:
                Broker broker = new Broker(name, surname, personType);
                broker.setCredentials(credentials);
                return brokerRepository.save(broker);
            case Constants.ADMIN:
                Admin admin = new Admin(name, surname, personType);
                admin.setCredentials(credentials);
                return adminRepository.save(admin);
            default:
                return null;
        }
    }

    public boolean signIn(@NotNull final Map<String, Object> data) {
        final Credentials credentialsWithoutId = Parser.parseCredentials(data.get("credentials"));
        if (credentialsRepository
                .findByLoginAndPassword(credentialsWithoutId.getLogin(), credentialsWithoutId.getPassword())
                .isPresent()) {
            final Credentials credentials = credentialsRepository.findByLoginAndPassword(
                    credentialsWithoutId.getLogin(),
                    credentialsWithoutId.getPassword()).get();
            final String personType = data.get("personType").toString();
            try {
                switch (personType) {
                    case Constants.CLIENT:
                        if (clientRepository.findByCredentials(credentials).isPresent()) {
                            Client client = clientRepository.findByCredentials(credentials).get();
                            client.setIsAuthenticated(true);
                            clientRepository.save(client);
                            return true;
                        }
                    case Constants.BROKER:
                        if (brokerRepository.findByCredentials(credentials).isPresent()) {
                            Broker broker = brokerRepository.findByCredentials(credentials).get();
                            broker.setIsAuthenticated(true);
                            brokerRepository.save(broker);
                            return true;
                        }
                    case Constants.ADMIN:
                        if (adminRepository.findByCredentials(credentials).isPresent()) {
                            Admin admin = adminRepository.findByCredentials(credentials).get();
                            admin.setIsAuthenticated(true);
                            adminRepository.save(admin);
                            return true;
                        }
                }
            } catch (Exception ignored) {
            }
        }
        return false;
    }

    public boolean signOut(@NotNull final Map<String, Object> data) {
        final Long id = Long.parseLong(data.get("id").toString());
        final String personType = data.get("personType").toString();
        switch (personType) {
            case Constants.CLIENT:
                if (clientRepository.findById(id).isPresent()) {
                    Client client = clientRepository.findById(id).get();
                    client.setIsAuthenticated(false);
                    clientRepository.save(client);
                    return true;
                }
                break;
            case Constants.BROKER:
                if (brokerRepository.findById(id).isPresent()) {
                    Broker broker = brokerRepository.findById(id).get();
                    broker.setIsAuthenticated(false);
                    brokerRepository.save(broker);
                    return true;
                }
                break;
            case Constants.ADMIN:
                if (adminRepository.findById(id).isPresent()) {
                    Admin admin = adminRepository.findById(id).get();
                    admin.setIsAuthenticated(false);
                    adminRepository.save(admin);
                    return true;
                }
                break;
        }
        return false;
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
