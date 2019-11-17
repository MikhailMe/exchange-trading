package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = Constants.System.STOCKS)
@EqualsAndHashCode(callSuper = true)
public final class Stock extends AbstractEntity {

    @Column(name = "money")
    private long amount;

    @Column(name = "course")
    private String course;

    @Column(name = "stockType")
    private String stockType;

    public Stock(final long amount,
                 @NotNull final String course,
                 @NotNull final String stockType) {
        this.amount = amount;
        this.course = course;
        this.stockType = stockType;
    }

}
