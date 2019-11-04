package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.BROKERAGE_ACCOUNT)
public final class BrokerageAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "number")
    private long number;

    @Column(name = "money")
    private long money;

    @Column(name = "currency")
    private String currency;

    @Column(name = "creation_date")
    private Instant creationDate;

    @JoinColumn(name = "stock_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Stock stock;

    public BrokerageAccount(long number, long money, String currency, Instant creationDate) {
        this.number = number;
        this.money = money;
        this.currency = currency;
        this.creationDate = creationDate;
    }

}
