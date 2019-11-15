package com.kspt.exchangetrading.models.system;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.AbstractEntity;
import lombok.*;

import javax.persistence.*;

@Data
@Entity
@ToString
@NoArgsConstructor
@Table(name = Constants.PASSPORT)
public final class Passport extends AbstractEntity {

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
