package com.kspt.exchangetrading.configuration;

public class Constants {

    public static class Actor {
        public static final String ADMIN = "admin";
        public static final String BROKER = "broker";
        public static final String CLIENT = "client";
    }

    public static class Validity {
        public static final String YEAR = "year";
        public static final String HALF_YEAR = "half year";
        public static final String MONTH = "month";
    }

    public static class ValidityInts {
        public static final long YEAR = 365L;
        public static final long HALF_YEAR = 182;
        public static final long MONTH = 30;
    }

    public static class Request {
        public static final String ADMIN_REQUEST = "admin_request";
        public static final String BROKER_REQUEST = "broker_request";
        public static final String CLIENT_REQUEST = "client_request";
    }

    public static class Exchange {
        public static final String MONEY_TO_STOCKS = "moneyToStocks";
        public static final String STOCKS_TO_MONEY = "stocksToMoney";
    }

    public static class System {
        public static final String STOCKS = "stocks";
        public static final String TRANSFER = "transfer";
        public static final String PASSPORT = "passport";
        public static final String CURRENCY = "currency";
        public static final String AGREEMENT = "agreement";
        public static final String CREDENTIALS = "credentials";
        public static final String BROKERAGE_ACCOUNT = "brokerage_account";
    }

}
