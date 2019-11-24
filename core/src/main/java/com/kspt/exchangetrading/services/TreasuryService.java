package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.treasury.*;
import com.kspt.exchangetrading.repositories.treasury.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreasuryService {

    private final RateRepository rateRepository;
    private final BankRecordRepository bankRecordRepository;

    public final AssetRepository assetRepository;
    public final StockRepository stockRepository;
    public final TransactionRepository transactionRepository;

    public TreasuryService(@NotNull final RateRepository rateRepository,
                           @NotNull final AssetRepository assetRepository,
                           @NotNull final StockRepository stockRepository,
                           @NotNull final BankRecordRepository bankRecordRepository,
                           @NotNull final TransactionRepository transactionRepository) {
        this.rateRepository = rateRepository;
        this.assetRepository = assetRepository;
        this.stockRepository = stockRepository;
        this.bankRecordRepository = bankRecordRepository;
        this.transactionRepository = transactionRepository;
    }

    public void bankTransfer(@NotNull final String requestType,
                             @NotNull final Transaction transaction) {
        List<BankRecord> bankRecords = bankRecordRepository.findAll();
        if (bankRecords != null) {
            Asset asset = transaction.getAsset();
            Stock stock = transaction.getStock();
            switch (requestType) {
                case Constants.Exchange.MONEY_TO_STOCKS: {
                    for (BankRecord record : bankRecords) {
                        final Double currentBalance = record.getQuantity();
                        if (record.getType().equals(asset.getType())) {
                            final Double exchangeBalance = asset.getQuantity();
                            final Double newBalance = currentBalance + exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        } else if (record.getType().equals(stock.getStockType())) {
                            final Double exchangeBalance = exchangeMoneyToStocks(asset, stock);
                            final Double newBalance = currentBalance - exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        }
                    }
                    break;
                }
                case Constants.Exchange.STOCKS_TO_MONEY: {
                    for (BankRecord record : bankRecords) {
                        final Double currentBalance = record.getQuantity();
                        if (record.getType().equals(stock.getStockType())) {
                            final Double exchangeBalance = exchangeStocksToMoney(asset, stock);
                            final Double newBalance = currentBalance + exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        } else if (record.getType().equals(asset.getType())) {
                            final Double exchangeBalance = asset.getQuantity();
                            final Double newBalance = currentBalance - exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        }
                    }
                    break;
                }
            }
        }
    }

    public Double exchangeMoneyToStocks(@NotNull final Asset asset,
                                      @NotNull final Stock stock) {
        List<Rate> rates = rateRepository.findAll();
        double stockQuantity = 0d;
        if (rates != null) {
            for (Rate rate : rates)
                if (rate.getFromType().equals(asset.getType()) && rate.getToType().equals(stock.getStockType())) {
                    stockQuantity = asset.getQuantity() / rate.getRate();
                    break;
                }
        }
        return stockQuantity;
    }

    public Double exchangeStocksToMoney(@NotNull final Asset asset,
                                      @NotNull final Stock stock) {
        List<Rate> rates = rateRepository.findAll();
        double assetQuantity = 0L;
        if (rates != null) {
            for (Rate rate : rates)
                if (rate.getFromType().equals(stock.getStockType()) && rate.getToType().equals(asset.getType())) {
                    assetQuantity = stock.getQuantity() / rate.getRate();
                    break;
                }
        }
        return assetQuantity;
    }

    List<Rate> setRates() {
        List<Rate> rates = new ArrayList<>();

        // 1 mishcoin = 50000 ruble
        // 1 ruble = 0.00002 mishcoin
        rates.add(new Rate(Constants.Currency.RUBLE, Constants.StockType.MISHCOIN, 50_000d));
        rates.add(new Rate(Constants.StockType.MISHCOIN, Constants.Currency.RUBLE, 0.00002d));

        // 1 cloudflare = 70000 ruble
        // 1 ruble = 0.000014 cloudflare
        rates.add(new Rate(Constants.Currency.RUBLE, Constants.StockType.CLOUD_FLARE, 70_000d));
        rates.add(new Rate(Constants.StockType.CLOUD_FLARE, Constants.Currency.RUBLE, 0.000014d));

        // 1 reatlyincome = 38000 ruble
        // 1 ruble = 0.000026 reatlyincome
        rates.add(new Rate(Constants.Currency.RUBLE, Constants.StockType.REALTY_INCOME, 38_000d));
        rates.add(new Rate(Constants.StockType.REALTY_INCOME, Constants.Currency.RUBLE, 0.000026d));


        // 1 mishcoin = 840 dollar
        // 1 dollar = 0.0012 mishcoin
        rates.add(new Rate(Constants.Currency.DOLLAR, Constants.StockType.MISHCOIN, 840d));
        rates.add(new Rate(Constants.StockType.MISHCOIN, Constants.Currency.DOLLAR, 0.0012d));

        // 1 cloudflare = 1170 dollar
        // 1 dollar = 0.0016 cloudflare
        rates.add(new Rate(Constants.Currency.DOLLAR, Constants.StockType.CLOUD_FLARE, 1170d));
        rates.add(new Rate(Constants.StockType.CLOUD_FLARE, Constants.Currency.DOLLAR, 0.00085d));

        // 1 reatlyincome = 630 dollar
        // 1 dollar = 0.0016 reatlyincome
        rates.add(new Rate(Constants.Currency.DOLLAR, Constants.StockType.REALTY_INCOME, 630d));
        rates.add(new Rate(Constants.StockType.REALTY_INCOME, Constants.Currency.DOLLAR, 0.0016d));


        // 1 mishcoin = 715 euro
        // 1 euro = 0.0014 mishcoin
        rates.add(new Rate(Constants.Currency.EURO, Constants.StockType.MISHCOIN, 715d));
        rates.add(new Rate(Constants.StockType.MISHCOIN, Constants.Currency.EURO, 0.0014d));

        // 1 cloudflare = 1000 euro
        // 1 euro = 0.001 cloudflare
        rates.add(new Rate(Constants.Currency.EURO, Constants.StockType.CLOUD_FLARE, 1000d));
        rates.add(new Rate(Constants.StockType.CLOUD_FLARE, Constants.Currency.EURO, 0.001d));

        // 1 reatlyincome = 543 euro
        // 1 euro = 0.0018 reatlyincome
        rates.add(new Rate(Constants.Currency.EURO, Constants.StockType.REALTY_INCOME, 543d));
        rates.add(new Rate(Constants.StockType.REALTY_INCOME, Constants.Currency.EURO, 0.0018d));

        for (Rate rate : rates) {
            rateRepository.save(rate);
        }

        return rates;
    }

    List<BankRecord> setBank() {
        List<BankRecord> bankRecords = new ArrayList<>();

        bankRecords.add(new BankRecord(Constants.Currency.RUBLE, 1_000_000_000d));
        bankRecords.add(new BankRecord(Constants.Currency.DOLLAR, 1_000_000_000d));
        bankRecords.add(new BankRecord(Constants.Currency.EURO, 1_000_000_000d));

        bankRecords.add(new BankRecord(Constants.StockType.MISHCOIN, 1_000_000d));
        bankRecords.add(new BankRecord(Constants.StockType.CLOUD_FLARE, 1_000_000d));
        bankRecords.add(new BankRecord(Constants.StockType.REALTY_INCOME, 1_000_000d));

        for (BankRecord bankRecord : bankRecords) {
            bankRecordRepository.save(bankRecord);
        }

        return bankRecords;
    }

    public List<Rate> getRates() {
        return rateRepository.findAll();
    }

    public List<BankRecord> getBankMoney() {
        return bankRecordRepository.findAll();
    }

}
