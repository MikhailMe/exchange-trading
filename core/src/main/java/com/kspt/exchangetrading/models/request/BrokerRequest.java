package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.enums.RequestType;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.actors.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = Constants.BROKER_REQUEST)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BrokerRequest extends Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @JoinColumn(name = "client_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Client client;

    @Column(name = "money")
    private long money;

    @Column(name = "requestType")
    private RequestType requestType;

    public BrokerRequest(@NotNull final Person from,
                         @NotNull final Person to,
                         @NotNull final Instant date) {
        super(from, to, date);
    }

}
