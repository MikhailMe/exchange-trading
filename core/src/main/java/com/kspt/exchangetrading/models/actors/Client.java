package com.kspt.exchangetrading.models.actors;

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
@NoArgsConstructor
@Table(name = Constants.Actor.CLIENT)
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    @JoinColumn(name = "passportId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Passport passport;

    @JoinColumn(name = "agreementId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Agreement agreement;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<ClientRequest> requests;

    @JoinColumn(name = "brokerageAccountId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BrokerageAccount brokerageAccount;

    public Client(@NotNull final String name,
                  @NotNull final String surname,
                  @NotNull final String personType) {
        this.name = name;
        this.surname = surname;
        this.personType = personType;
    }
}
