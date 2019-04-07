package com.kspt.exchangetrading.models.util.enums;

import lombok.Getter;
import lombok.Setter;

public enum Validity {

    MONTH(0),
    HALF_YEAR(1),
    YEAR(2);

    @Getter
    @Setter
    private int code;

    Validity(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (code) {
            case 0:
                return MONTH.toString();
            case 1:
                return HALF_YEAR.toString();
            case 2:
                return YEAR.toString();
            default:
                return "invalid validity";
        }
    }
}
