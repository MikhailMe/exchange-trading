package com.kspt.exchangetrading.configuration;

public class Constants {

    public static class Actor {
        public static final String ADMIN = "admin";
        public static final String BROKER = "broker";
        public static final String CLIENT = "client";
    }

    public static class Currency {
        public static final String EURO = "euro";
        public static final String RUBLE = "ruble";
        public static final String DOLLAR = "dollar";
    }
    public static class StockType {
        public static final String MISHCOIN = "mishcoin";
        public static final String REALTY_INCOME = "realty income";
        public static final String CLOUD_FLARE = "cloud flare";
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
        public static final String CLIENT_REQUEST = "client_request";
    }

    public static class ClientRequestStatus {
        public static final String DECLINED = "declined";
        public static final String COMPLETED = "completed";
        public static final String PROCESSING = "processing";
        public static final String APPROVED_BY_BROKER = "approved by broker";
    }

    public static class Exchange {
        public static final String MONEY_TO_STOCKS = "moneyToStocks";
        public static final String STOCKS_TO_MONEY = "stocksToMoney";
    }

    public static class System {
        public static final String ASSET = "asset";
        public static final String STOCKS = "stocks";
        public static final String PASSPORT = "passport";
        public static final String CURRENCY = "currency";
        public static final String AGREEMENT = "agreement";
        public static final String CREDENTIALS = "credentials";
        public static final String BROKERAGE_ACCOUNT = "brokerage_account";
    }

    public static class Status {
        public static final String SUCCESS = "success";
        public static final String FAILURE = "failure";
    }

}
