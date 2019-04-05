package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    String INSERT_CLIENT = "INSERT INTO client(name, surname) VALUES(:name, :surname)";

    String UPDATE_CLIENT = "UPDATE client SET name";

    @Query(nativeQuery = true, value = UPDATE_CLIENT)
    Client update(Client client);

    @Query(nativeQuery = true, value = INSERT_CLIENT)
    Optional<Client> create(@Param("name") String name,
                            @Param("surname") String surname);

}
