package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.enums.RequestType;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.actors.Client;
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
@Table(name = Constants.BROKER_REQUEST)
public class BrokerRequest extends Request {

    @JoinColumn(name = "admin_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Admin to;

    @JoinColumn(name = "broker_id")
    @OneToOne(fetch = FetchType.LAZY)
    protected Broker from;

    @JoinColumn(name = "client_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "money")
    private long money;

    @Column(name = "requestType")
    private RequestType requestType;

    public BrokerRequest(@NotNull final Instant date) {
        super(date);
    }

}
