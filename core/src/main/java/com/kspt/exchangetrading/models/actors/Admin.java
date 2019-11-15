package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.request.Request;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Table;
import javax.persistence.Entity;
import javax.persistence.Transient;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.ADMIN)
public class Admin extends Person {

    @Transient
    @JsonIgnore
    private List<Broker> brokers;

    @Transient
    @JsonIgnore
    private List<Request> requests;

    public Admin(@NotNull final String name,
                 @NotNull final String surname) {
        super(name, surname);
    }
}

