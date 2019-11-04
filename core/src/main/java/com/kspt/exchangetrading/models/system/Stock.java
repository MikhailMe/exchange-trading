package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.STOCKS)
public final class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "money")
    private long amount;

    @Column(name = "course")
    private String course;

    @Column(name = "stockCurrency")
    private String stockCurrency;

    public Stock(final long amount,
                 @NotNull final String course,
                 @NotNull final String stockCurrency) {
        this.amount = amount;
        this.course = course;
        this.stockCurrency = stockCurrency;
    }

}
