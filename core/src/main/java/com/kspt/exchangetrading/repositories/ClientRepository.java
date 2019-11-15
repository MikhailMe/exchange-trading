package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.Passport;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends CommonRepository<Client> {

    // <S extends Client> S
    Client findByPassport(@NotNull final Passport passport);

    //String UPDATE_CLIENT = "UPDATE client SET name;";

/*    @Override
    <S extends Client> S save(S client);*/

    /*@Query(nativeQuery = true, value = UPDATE_CLIENT)
    Client update(Client client);
*/



}
