package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.actors.Person;
import com.kspt.exchangetrading.models.system.Credentials;
import com.kspt.exchangetrading.models.treasury.BankRecord;
import com.kspt.exchangetrading.models.treasury.Rate;
import com.kspt.exchangetrading.repositories.actors.AdminRepository;
import com.kspt.exchangetrading.repositories.actors.BrokerRepository;
import com.kspt.exchangetrading.repositories.actors.ClientRepository;
import com.kspt.exchangetrading.repositories.system.CredentialsRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SystemService {

    private final TreasuryService treasuryService;
    private final AdminRepository adminRepository;
    private final ClientRepository clientRepository;
    private final BrokerRepository brokerRepository;
    private final CredentialsRepository credentialsRepository;

    public SystemService(@NotNull final TreasuryService treasuryService,
                         @NotNull final AdminRepository adminRepository,
                         @NotNull final ClientRepository clientRepository,
                         @NotNull final BrokerRepository brokerRepository,
                         @NotNull final CredentialsRepository credentialsRepository) {
        this.treasuryService = treasuryService;
        this.adminRepository = adminRepository;
        this.clientRepository = clientRepository;
        this.brokerRepository = brokerRepository;
        this.credentialsRepository = credentialsRepository;
    }

    public Person signUp(@NotNull final Map<String, Object> data) {
        final String login = data.get("login").toString();
        final String password = data.get("password").toString();
        final Credentials credentials = new Credentials(login, password);

        final String personType = data.get("personType").toString();
        final String name = data.get("name").toString();
        final String surname = data.get("surname").toString();

        switch (personType) {
            case Constants.Actor.CLIENT: {
                Client client = new Client(name, surname, personType);
                client.setCredentials(credentials);
                return clientRepository.save(client);
            }
            case Constants.Actor.BROKER: {
                Broker broker = new Broker(name, surname, personType);
                broker.setCredentials(credentials);
                // map admin for broker
                Broker savedBroker = brokerRepository.save(broker);
                Admin vacantAdmin = getVacantAdmin(adminRepository.findAll(), savedBroker.getId());
                savedBroker.setAdminId(vacantAdmin.getId());
                // update brokers in admin
                List<Long> brokers = vacantAdmin.getBrokers();
                brokers.add(savedBroker.getId());
                vacantAdmin.setBrokers(brokers);
                adminRepository.save(vacantAdmin);
                return brokerRepository.save(savedBroker);
            }
            case Constants.Actor.ADMIN: {
                Admin admin = new Admin(name, surname, personType);
                admin.setCredentials(credentials);
                return adminRepository.save(admin);
            }
            default:
                return null;
        }
    }

    public boolean signIn(@NotNull final Map<String, Object> data) {
        final String login = data.get("login").toString();
        final String password = data.get("password").toString();
        final Credentials credentialsWithoutId = new Credentials(login, password);
        final Credentials credentials = credentialsRepository.findByLoginAndPassword(
                credentialsWithoutId.getLogin(),
                credentialsWithoutId.getPassword()).orElse(null);
        if (credentials != null) {
            final String personType = data.get("personType").toString();
            try {
                switch (personType) {
                    case Constants.Actor.CLIENT: {
                        Client client = clientRepository.findByCredentials(credentials).orElse(null);
                        if (client != null) {
                            client.setIsAuthenticated(true);
                            clientRepository.save(client);
                            return true;
                        }
                        break;
                    }
                    case Constants.Actor.BROKER: {
                        Broker broker = brokerRepository.findByCredentials(credentials).orElse(null);
                        if (broker != null) {
                            broker.setIsAuthenticated(true);
                            brokerRepository.save(broker);
                            return true;
                        }
                        break;
                    }
                    case Constants.Actor.ADMIN: {
                        Admin admin = adminRepository.findByCredentials(credentials).orElse(null);
                        if (admin != null) {
                            admin.setIsAuthenticated(true);
                            adminRepository.save(admin);
                            return true;
                        }
                        break;
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
            case Constants.Actor.CLIENT: {
                Client client = clientRepository.findById(id).orElse(null);
                if (client != null) {
                    client.setIsAuthenticated(false);
                    clientRepository.save(client);
                    return true;
                }
                break;
            }
            case Constants.Actor.BROKER: {
                Broker broker = brokerRepository.findById(id).orElse(null);
                if (broker != null) {
                    broker.setIsAuthenticated(false);
                    brokerRepository.save(broker);
                    return true;
                }
                break;
            }
            case Constants.Actor.ADMIN: {
                Admin admin = adminRepository.findById(id).orElse(null);
                if (admin != null) {
                    admin.setIsAuthenticated(false);
                    adminRepository.save(admin);
                    return true;
                }
                break;
            }
        }
        return false;
    }

    public List<BankRecord> setBankAssets() {
        return treasuryService.setBank();
    }

    public List<Rate> setRates() {
        return treasuryService.setRates();
    }

    private Admin getVacantAdmin(@NotNull final List<Admin> admins,
                                 @NotNull final Long brokerId) {
        Admin minAdmin = admins.get(0);
        int brokersSize = Integer.MAX_VALUE;
        for (Admin admin : admins) {
            if (admin.getBrokers() != null && admin.getBrokers().size() < brokersSize) {
                brokersSize = admin.getBrokers().size();
                minAdmin = admin;
            }
        }
        if (!minAdmin.getBrokers().contains(brokerId)) {
            return minAdmin;
        } else {
            admins.remove(minAdmin);
            return getVacantAdmin(admins, brokerId);
        }
    }
}
