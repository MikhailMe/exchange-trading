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
@Table(name = Constants.System.AGREEMENT)
@EqualsAndHashCode(callSuper = true)
public final class Agreement extends AbstractEntity {

    @Column(name = "clientId")
    private Long clientId;

    @Column(name = "brokerId")
    private Long brokerId;

    @Column(name = "validity")
    private String validity;

    @Column(name = "startDate")
    private Instant startDate;

    public Agreement(@NotNull final String validity,
                     @NotNull final Instant startDate) {
        this.validity = validity;
        this.startDate = startDate;
    }

}
