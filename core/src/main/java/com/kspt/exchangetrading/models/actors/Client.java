package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.models.system.Request;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Passport;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.util.List;

import static com.kspt.exchangetrading.configuration.Constants.CLIENT;

@Data
@Entity
@NoArgsConstructor
@Table(name = CLIENT)
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    @JoinColumn(name = "broker_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Broker broker;

    @JoinColumn(name = "passport_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @JoinColumn(name = "agreement_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Agreement agreement;

    @Transient
    @JsonIgnore
    private List<Request> requests;

    @JoinColumn(name = "brokerage_account_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private BrokerageAccount brokerageAccount;

    public Client(@NotNull final String name,
                  @NotNull final String surname) {
        super(name, surname, CLIENT);
    }

}
