package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.System.BROKERAGE_ACCOUNT)
public final class BrokerageAccount extends AbstractEntity {

    @Column(name = "money")
    private Long money;

    @Column(name = "currency")
    private String currency;

    @Column(name = "clientPassportId")
    private Long clientPassportId;

    @Column(name = "creationDate")
    private Instant creationDate;

    @ElementCollection(fetch = FetchType.LAZY)
    private Set<Stock> stocks;

    public BrokerageAccount(@NotNull final Long money,
                            @NotNull final String currency) {
        this.money = money;
        this.currency = currency;
        this.creationDate = Instant.now();
        this.stocks = new HashSet<>();
    }

}
