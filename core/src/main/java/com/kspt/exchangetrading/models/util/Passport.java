package com.kspt.exchangetrading.models.util;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class Passport {

    private String name;

    private String surname;

    private int series;

    private int number;

}
