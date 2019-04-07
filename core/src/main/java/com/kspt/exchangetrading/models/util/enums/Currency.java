package com.kspt.exchangetrading.models.util.enums;

import lombok.Getter;
import lombok.Setter;

public enum Currency {

    EURO(0),
    RUBLE(1),
    DOLLAR(2);

    @Getter
    @Setter
    private int code;

    Currency(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (code) {
            case 0:
                return EURO.toString();
            case 1:
                return RUBLE.toString();
            case 2:
                return DOLLAR.toString();
            default:
                return "invalid currency";
        }
    }
}
