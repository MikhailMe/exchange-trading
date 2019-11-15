package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.system.Passport;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.CLIENT_REQUEST)
public class ClientRequest extends Request {

    @JoinColumn(name = "broker_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Broker to;

    @JoinColumn(name = "client_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Client from;

    @JoinColumn(name = "passport_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @JoinColumn(name = "brokerage_account_id")
    @OneToOne(fetch = FetchType.LAZY)
    private BrokerageAccount brokerageAccount;

    public ClientRequest(@NotNull final Instant date) {
        super(date);
    }

}
