package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = Constants.PASSPORT)
public final class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "series")
    private int series;

    @Column(name = "number")
    private int number;

    public Passport(final int series,
                    final int number) {
        this.series = series;
        this.number = number;
    }

}
