package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.TRANSACTION)
public final class Transaction extends AbstractEntity {

    @Column(name = "adminId")
    private Long adminId;

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    private Instant date;

    @JoinColumn(name = "assetId")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Asset asset;

    @JoinColumn(name = "stockId")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Stock stock;

    public Transaction(@NotNull final Long adminId,
                       @NotNull final Long clientId,
                       @NotNull final String type) {
        this.type = type;
        this.adminId = adminId;
        this.clientId = clientId;
        this.date = Instant.now();
    }
}
