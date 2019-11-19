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

    @Column(name = "amount")
    private Long amount;

    @Column(name = "stockType")
    private String stockType;

    public Stock(@NotNull final Long amount,
                 @NotNull final String stockType) {
        this.amount = amount;
        this.stockType = stockType;
    }

}
