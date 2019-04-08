package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.models.system.Request;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import java.util.List;

import static com.kspt.exchangetrading.configuration.Constants.BROKER;

@Data
@Entity
@Table(name = BROKER)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Broker extends Person {

    @Transient
    @JsonIgnore
    private List<Client> clients;

    @Transient
    @JsonIgnore
    private List<Request> requests;

    public Broker(@NotNull final String name,
                  @NotNull final String surname) {
        super(name, surname, BROKER);
    }

}
