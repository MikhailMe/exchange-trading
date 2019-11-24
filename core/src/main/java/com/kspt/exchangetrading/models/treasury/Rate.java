package com.kspt.exchangetrading.models.treasury;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = Constants.Treasury.RATE)
public final class Rate extends AbstractEntity {

    // TO = 1
    // FROM / RATE = TO

    @Column(name = "toType")
    private String toType;

    @Column(name = "fromType")
    private String fromType;

    @Column(name = "rate")
    private Double rate;

    @Column(name = "date")
    private Instant date;

    public Rate(@NotNull final String fromType,
                @NotNull final String toType,
                @NotNull final Double rate) {
        this.fromType = fromType;
        this.toType = toType;
        this.rate = rate;
        this.date = Instant.now();
    }
}
