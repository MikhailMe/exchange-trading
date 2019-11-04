package com.kspt.exchangetrading.enums;

import org.jetbrains.annotations.NotNull;

public enum Validity {
    YEAR("year"),
    MONTH("month"),
    HALF_YEAR("half_year");

    private String validity;

    Validity(@NotNull final String validity) {
        this.validity = validity;
    }

    @Override
    public String toString() {
        return this.validity;
    }

}
