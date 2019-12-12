package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.CrudController;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.ClientRequest;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.treasury.BankRecord;
import com.kspt.exchangetrading.models.treasury.Rate;
import com.kspt.exchangetrading.models.treasury.Transaction;
import com.kspt.exchangetrading.services.actors.AdminService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.Actor.ADMIN)
public final class AdminController extends CrudController<Admin, AdminService> {

    public AdminController(@NotNull AdminService service) {
        super(service);
    }

    @GetMapping("{adminId}/checkRequests")
    public List<ClientRequest> checkRequests(@PathVariable final Long adminId) {
        return service.checkApprovedByBrokerRequests(adminId);
    }

    // clientRequestId
    @PostMapping("{adminId}/approveRequest")
    public Transaction approveRequest(@PathVariable final Long adminId,
                                      @RequestBody final Map<String, String> data) {
        return service.approveRequest(adminId, data);
    }

    // clientRequestId
    @PostMapping("declineRequest/{clientRequestId}")
    public void declineRequest(@PathVariable final Long clientRequestId) {
        service.declineRequest(clientRequestId);
    }

    @GetMapping("getRates")
    public List<Rate> getRates() {
        return service.getRates();
    }

    @GetMapping("getBankAssets")
    public List<BankRecord> getBankMoney() {
        return service.getBankMoney();
    }

    @GetMapping("{adminId}/getBrokers")
    public List<Broker> getBrokers(@PathVariable final Long adminId) {
        return service.getBrokers(adminId);
    }
}
