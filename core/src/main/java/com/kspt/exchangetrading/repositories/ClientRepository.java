package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClientRepository extends JpaRepository<Client, Long> {

    String UPDATE_CLIENT = "UPDATE client SET name;";

    @Override
    <S extends Client> S save(S client);

    @Query(nativeQuery = true, value = UPDATE_CLIENT)
    Client update(Client client);




}
