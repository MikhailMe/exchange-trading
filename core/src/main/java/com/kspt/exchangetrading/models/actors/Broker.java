package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.request.BrokerRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.BROKER)
public class Broker extends Person {

    @Transient
    @JsonIgnore
    private List<Client> clients;

    @Transient
    @JsonIgnore
    private List<BrokerRequest> requests;

    public Broker(@NotNull final String name,
                  @NotNull final String surname) {
        super(name, surname);
    }

}
