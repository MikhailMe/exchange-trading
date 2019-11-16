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

import javax.persistence.*;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.CLIENT)
@EqualsAndHashCode(callSuper = true)
public class Client extends Person {

    @JoinColumn(name = "passport_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Passport passport;

    @JoinColumn(name = "agreement_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Agreement agreement;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<ClientRequest> requests;

    @JoinColumn(name = "brokerage_account_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private BrokerageAccount brokerageAccount;

}
