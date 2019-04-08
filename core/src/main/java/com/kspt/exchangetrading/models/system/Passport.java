package com.kspt.exchangetrading.models.system;

import lombok.*;

import javax.persistence.*;

import static com.kspt.exchangetrading.configuration.Constants.PASSPORT;

@Data
@Entity
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Table(name = PASSPORT)
public final class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "series")
    private int series;

    @Column(name = "number")
    private int number;

}
