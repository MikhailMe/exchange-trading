package com.kspt.exchangetrading.models.request;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.actors.Person;
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
@Table(name = Constants.CLIENT_REQUEST)
public class ClientRequest extends Request {

    @JoinColumn(name = "passport_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Passport passport;

    @JoinColumn(name = "brokerage_account_id")
    @OneToOne(fetch = FetchType.LAZY)
    private BrokerageAccount brokerageAccount;

    public ClientRequest(@NotNull final Person from,
                         @NotNull final Person to,
                         @NotNull final Instant date) {
        super(from, to, date);
    }

}
