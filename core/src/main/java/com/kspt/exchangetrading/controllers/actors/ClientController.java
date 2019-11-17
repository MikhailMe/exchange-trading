package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.AbstractController;
import com.kspt.exchangetrading.models.actors.Client;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.models.system.BrokerageAccount;
import com.kspt.exchangetrading.services.actors.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(Constants.Actor.CLIENT)
public class ClientController extends AbstractController<Client, ClientService> {

    public ClientController(ClientService clientService) {
        super(clientService);
    }

    // contract: clientId, currency
    @PostMapping("openBrokerageAccount")
    public BrokerageAccount openBrokerageAccount(@RequestBody final Map<String, Object> data) {
        return service.openBrokerageAccount(data);
    }

    // contract: clientId
    @PostMapping("closeBrokerageAccount/{clientId}")
    public boolean closeBrokerageAccount(@PathVariable final Long clientId) {
        return service.closeBrokerageAccount(clientId);
    }

    // contract: clientId, brokerageAccountId, money
    @PostMapping("putMoneyToAccount")
    public boolean putMoneyToAccount(@RequestBody final Map<String, Object> data) {
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

    // contract: clientId, moneyAmount, stockType(mishcoin, realtyincome, cloudflare)
    @PostMapping("exchangeMoneyToStocks")
    public ClientRequest exchangeMoneyForStocks(@RequestBody final Map<String, Object> data) {
        return service.exchangeMoneyToStocks(data);
    }

    // contract: clientId, stockAmount, currency(ruble, dollar, euro)
    @PostMapping("exchangeStocksToMoney")
    public ClientRequest exchangeStocksToMoney(@RequestBody final Map<String, Object> data) {
        return service.exchangeStocksToMoney(data);
    }
}
