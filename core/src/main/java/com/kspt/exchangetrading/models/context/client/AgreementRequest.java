package com.kspt.exchangetrading.models.context.client;

import com.kspt.exchangetrading.models.system.Passport;
import lombok.Data;

@Data
public final class AgreementRequest {

    private Passport passport;

    private String validity;

    private long numberBrokerageAccount;


}
