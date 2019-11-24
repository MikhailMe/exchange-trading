package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.STOCK)
public final class Stock extends AbstractEntity {

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "stockType")
    private String stockType;

    @Column(name = "quantity")
    private Double quantity;

    public Stock(@NotNull final Long clientId,
                 @NotNull final String stockType) {
        this.clientId = clientId;
        this.stockType = stockType;
        this.quantity = 0d;
    }
}
