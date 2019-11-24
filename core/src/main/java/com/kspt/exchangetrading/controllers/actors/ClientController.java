package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.CrudController;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.models.treasury.Transaction;
import com.kspt.exchangetrading.services.actors.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.Actor.CLIENT)
public final class ClientController extends CrudController<Client, ClientService> {

    public ClientController(ClientService clientService) {
        super(clientService);
    }

    // contract: clientId
    @PostMapping("openBrokerageAccount/{clientId}")
    public BrokerageAccount openBrokerageAccount(@PathVariable final Long clientId) {
        return service.openBrokerageAccount(clientId);
    }

    // contract: clientId
    @PostMapping("closeBrokerageAccount/{clientId}")
    public boolean closeBrokerageAccount(@PathVariable final Long clientId) {
        return service.closeBrokerageAccount(clientId);
    }

    // contract: clientId, money, currency
    @PostMapping("putMoneyToAccount")
    public boolean putMoneyToAccount(@RequestBody final Map<String, String> data) {
        return service.putMoneyToAccount(data);
    }

    // contract: clientId, validity
    @PostMapping("makeBrokerAgreement")
    public Agreement makeBrokerAgreement(@RequestBody final Map<String, Object> data) {
        return service.makeBrokerAgreement(data);
    }

    // contract: clientId, validity
    @PostMapping("extendBrokerAgreement")
    public Agreement extendBrokerAgreement(@RequestBody final Map<String, Object> data) {
        return service.extendBrokerAgreement(data);
    }

    // contract: clientId
    @PostMapping("breakBrokerAgreement/{clientId}")
    public boolean breakBrokerAgreement(@PathVariable final Long clientId) {
        return service.breakBrokerAgreement(clientId);
    }

    // contract: clientId, quantity, fromType(ruble, dollar, euro), toType(mishcoin, realtyincome, cloudflare)
    @PostMapping("exchangeMoneyToStocks")
    public ClientRequest exchangeMoneyForStocks(@RequestBody final Map<String, Object> data) {
        return service.exchange(data, Constants.Exchange.MONEY_TO_STOCKS);
    }

    // contract: clientId, quantity, fromType(mishcoin, realtyincome, cloudflare), toType (ruble, dollar, euro)
    @PostMapping("exchangeStocksToMoney")
    public ClientRequest exchangeStocksToMoney(@RequestBody final Map<String, Object> data) {
        return service.exchange(data, Constants.Exchange.STOCKS_TO_MONEY);
    }

    // contract: clientId
    @GetMapping("{clientId}/getTransactions")
    public List<Transaction> getTransactions(@PathVariable final Long clientId) {
        return service.getTransactions(clientId);
    }
}
