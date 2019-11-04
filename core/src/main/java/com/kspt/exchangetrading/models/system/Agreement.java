package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.AGREEMENT)
public final class Agreement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "validity")
    private String validity;

    @Column(name = "start_date")
    private Instant startDate;

    public Agreement(@NotNull final String validity,
                     @NotNull final Instant startDate) {
        this.validity = validity;
        this.startDate = startDate;
    }

}
