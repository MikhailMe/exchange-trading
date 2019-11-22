package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.models.treasury.*;
import com.kspt.exchangetrading.repositories.treasury.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

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
                        final Long currentBalance = record.getQuantity();
                        if (record.getType().equals(asset.getType())) {
                            final Long exchangeBalance = asset.getQuantity();
                            final Long newBalance = currentBalance + exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        } else if (record.getType().equals(stock.getStockType())) {
                            final Long exchangeBalance = exchangeMoneyToStocks(asset, stock);
                            final Long newBalance = currentBalance - exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        }
                    }
                    break;
                }
                case Constants.Exchange.STOCKS_TO_MONEY: {
                    for (BankRecord record : bankRecords) {
                        final Long currentBalance = record.getQuantity();
                        if (record.getType().equals(stock.getStockType())) {
                            final Long exchangeBalance = exchangeStocksToMoney(asset, stock);
                            final Long newBalance = currentBalance + exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        } else if (record.getType().equals(asset.getType())) {
                            final Long exchangeBalance = asset.getQuantity();
                            final Long newBalance = currentBalance - exchangeBalance;
                            record.setQuantity(newBalance);
                            bankRecordRepository.save(record);
                        }
                    }
                    break;
                }
            }
        }
    }

    public Long exchangeMoneyToStocks(@NotNull final Asset asset,
                                      @NotNull final Stock stock) {
        List<Rate> rates = rateRepository.findAll();
        long stockQuantity = 0L;
        if (rates != null) {
            for (Rate rate : rates)
                if (rate.getFromType().equals(asset.getType()) && rate.getToType().equals(stock.getStockType())) {
                    stockQuantity = asset.getQuantity() / rate.getRate();
                    break;
                }
        }
        return stockQuantity;
    }

    public Long exchangeStocksToMoney(@NotNull final Asset asset,
                                      @NotNull final Stock stock) {
        List<Rate> rates = rateRepository.findAll();
        long assetQuantity = 0L;
        if (rates != null) {
            for (Rate rate : rates)
                if (rate.getFromType().equals(stock.getStockType()) && rate.getToType().equals(asset.getType())) {
                    assetQuantity = stock.getQuantity() / rate.getRate();
                    break;
                }
        }
        return assetQuantity;
    }

}
