package com.kspt.exchangetrading.enums;

import org.jetbrains.annotations.NotNull;

public enum Currency {
    EURO("euro"),
    RUBLE("ruble"),
    DOLLAR("dollar");

    private String currency;

    Currency(@NotNull final String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return this.currency;
    }

}
