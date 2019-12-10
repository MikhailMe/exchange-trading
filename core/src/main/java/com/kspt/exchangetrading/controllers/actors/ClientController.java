package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.CrudController;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.ClientRequest;
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

    // contract: series, number
    @PostMapping("{clientId}/setPassport")
    public Client setPassport(@PathVariable final Long clientId,
                              @RequestBody final Map<String, String> data) {
        return service.setPassport(clientId, data);
    }

    @PostMapping("openBrokerageAccount/{clientId}")
    public BrokerageAccount openBrokerageAccount(@PathVariable final Long clientId) {
        return service.openBrokerageAccount(clientId);
    }

    @PostMapping("closeBrokerageAccount/{clientId}")
    public boolean closeBrokerageAccount(@PathVariable final Long clientId) {
        return service.closeBrokerageAccount(clientId);
    }

    // contract: money, currency
    @PostMapping("{clientId}/putMoneyToAccount")
    public boolean putMoneyToAccount(@PathVariable final Long clientId,
                                     @RequestBody final Map<String, String> data) {
        return service.putMoneyToAccount(clientId, data);
    }

    // contract: validity
    @PostMapping("{clientId}/makeBrokerAgreement")
    public Agreement makeBrokerAgreement(@PathVariable final Long clientId,
                                         @RequestBody final Map<String, Object> data) {
        return service.makeBrokerAgreement(clientId, data);
    }

    // contract: validity
    @PostMapping("{clientId}/extendBrokerAgreement")
    public Agreement extendBrokerAgreement(@PathVariable final Long clientId,
                                           @RequestBody final Map<String, Object> data) {
        return service.extendBrokerAgreement(clientId, data);
    }

    @PostMapping("{clientId}/breakBrokerAgreement")
    public boolean breakBrokerAgreement(@PathVariable final Long clientId) {
        return service.breakBrokerAgreement(clientId);
    }

    // contract: quantity, fromType(ruble, dollar, euro), toType(mishcoin, realtyincome, cloudflare)
    @PostMapping("{clientId}/exchangeMoneyToStocks")
    public ClientRequest exchangeMoneyForStocks(@PathVariable final Long clientId,
                                                @RequestBody final Map<String, Object> data) {
        return service.exchange(clientId, data, Constants.Exchange.MONEY_TO_STOCKS);
    }

    // contract: quantity, fromType(mishcoin, realtyincome, cloudflare), toType (ruble, dollar, euro)
    @PostMapping("{clientId}/exchangeStocksToMoney")
    public ClientRequest exchangeStocksToMoney(@PathVariable final Long clientId,
                                               @RequestBody final Map<String, Object> data) {
        return service.exchange(clientId, data, Constants.Exchange.STOCKS_TO_MONEY);
    }

    @GetMapping("{clientId}/getTransactions")
    public List<Transaction> getTransactions(@PathVariable final Long clientId) {
        return service.getTransactions(clientId);
    }
}
