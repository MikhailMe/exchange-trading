package com.kspt.exchangetrading.models.context.client;

import com.kspt.exchangetrading.models.system.Passport;

import lombok.*;

@Data
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public final class AccountRequest {

    private Passport passport;

    private String currency;

}
