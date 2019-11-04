package com.kspt.exchangetrading.enums;

import org.jetbrains.annotations.NotNull;

public enum RequestType {

    OPEN_BROKERAGE_ACCOUNT("open"),
    CLOSE_BROKERAGE_ACCOUNT("close"),

    CREATE_BROKER_AGREEMENT("create"),
    EXTEND_BROKER_AGREEMENT("extend"),
    BREAK_BROKER_AGREEMENT("break"),

    EXCHANGE_MONEY_TO_STOCKS("m2s"),
    EXCHANGE_STOCKS_TO_MONEY("s2m");

    private String requestType;

    RequestType(@NotNull final String requestType) {
        this.requestType = requestType;
    }

    @Override
    public String toString() {
        return this.requestType;
    }

}
