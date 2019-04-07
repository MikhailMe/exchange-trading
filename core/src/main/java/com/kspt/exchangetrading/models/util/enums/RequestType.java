package com.kspt.exchangetrading.models.util.enums;

import lombok.Getter;
import lombok.Setter;

public enum RequestType {

    OPEN_BROCKERAGE_ACCOUNT(0),
    CLOSE_BROCKERAGE_ACCOUNT(1),
    MAKE_BROKER_AGREEMENT(2),
    EXTEND_BROKER_AGREEMENT(3),
    BREAK_BROKER_AGREEMENT(4),
    EXCHANGE_MONEY_TO_STOCKS(5),
    EXCHANGE_STOCKS_TO_MONEY(6);

    @Getter
    @Setter
    private int code;

    RequestType(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        switch (code) {
            case 0:
                return OPEN_BROCKERAGE_ACCOUNT.toString();
            case 1:
                return CLOSE_BROCKERAGE_ACCOUNT.toString();
            case 2:
                return MAKE_BROKER_AGREEMENT.toString();
            case 3:
                return EXTEND_BROKER_AGREEMENT.toString();
            case 4:
                return BREAK_BROKER_AGREEMENT.toString();
            case 5:
                return EXCHANGE_MONEY_TO_STOCKS.toString();
            case 6:
                return EXCHANGE_STOCKS_TO_MONEY.toString();
            default:
                return "invalid request type";
        }
    }
}
