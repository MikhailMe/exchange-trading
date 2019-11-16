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
    private Long money;

    @Column(name = "currency")
    private String currency;

    @Column(name = "clientPassportId")
    private Long clientPassportId;

    @Column(name = "creationDate")
    private Instant creationDate;

    @JoinColumn(name = "stockId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;

    public BrokerageAccount(@NotNull final Long money,
                            @NotNull final String currency) {
        this.money = money;
        this.currency = currency;
        this.creationDate = Instant.now();
    }

}
