package com.kspt.exchangetrading.models.system;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

import static com.kspt.exchangetrading.configuration.Constants.AGREEMENT;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = AGREEMENT)
public final class Agreement implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "validity")
    private String validity;

    @Column(name = "start_date")
    private Instant startDate;

    @Column(name = "end_date")
    private Instant endDate;
}
