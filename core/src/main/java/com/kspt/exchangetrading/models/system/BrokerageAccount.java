package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.models.treasury.Asset;
import com.kspt.exchangetrading.models.treasury.Stock;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.System.BROKERAGE_ACCOUNT)
public final class BrokerageAccount extends AbstractEntity {

    @Column(name = "clientPassportId")
    private Long clientPassportId;

    @Column(name = "creationDate")
    private Instant creationDate;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Asset> assets;

    @ElementCollection(fetch = FetchType.LAZY)
    private List<Stock> stocks;

    public BrokerageAccount() {
        this.creationDate = Instant.now();
        this.assets = new ArrayList<>();
        this.stocks = new ArrayList<>();
    }

}
