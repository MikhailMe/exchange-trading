package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.enums.Currency;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.BROKERAGE_ACCOUNT)
public final class BrokerageAccount extends AbstractEntity {

    @Column(name = "money")
    private long money;

    @Column(name = "currency")
    private Currency currency;

    @Column(name = "creation_date")
    private Instant creationDate;

    @JoinColumn(name = "stock_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;

    public BrokerageAccount(final long money,
                            @NotNull final Currency currency) {
        this.money = money;
        this.currency = currency;
        this.creationDate = Instant.now();
    }

}
