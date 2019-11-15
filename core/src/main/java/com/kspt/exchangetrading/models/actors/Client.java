package com.kspt.exchangetrading.models.actors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Passport;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@Embeddable
@NoArgsConstructor
@Table(name = Constants.CLIENT)
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    @JoinColumn(name = "broker_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Broker broker;

    @JoinColumn(name = "passport_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Passport passport;

    @JoinColumn(name = "agreement_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Agreement agreement;

    @Transient
    @JsonIgnore
    private List<ClientRequest> requests;

    @JoinColumn(name = "brokerage_account_id")
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BrokerageAccount brokerageAccount;

    public Client(@NotNull final String name,
                  @NotNull final String surname) {
        super(name, surname);
    }

}
