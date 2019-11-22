package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;

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
    private Long quantity;
}
