package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.System.TRANSFER)
@EqualsAndHashCode(callSuper = true)
public final class Transfer extends AbstractEntity {

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "assetAmount")
    private long assetAmount;

    @Column(name = "date")
    private Instant date;

    @Column(name = "approver")
    private Long approverId;

    public Transfer(@NotNull final Long assetAmount,
                    @NotNull final Instant date) {
        this.assetAmount = assetAmount;
        this.date = date;
    }

}
