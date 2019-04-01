package com.kspt.exchangetrading.repositories;

/*
import com.kspt.exchangetrading.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
*/

public interface ClientRepository extends /*JpaRepository<Client, Long>,*/ IClientRepository {

    String INSERT_QUERY = "INSERT INTO clients VALUES(name, surname)";

    //@Query(nativeQuery =  true, value = INSERT_QUERY)
    String insertClient();

    //@Query(nativeQuery =  true, value = "test query")
    String createNoteForOpenBill();

}
