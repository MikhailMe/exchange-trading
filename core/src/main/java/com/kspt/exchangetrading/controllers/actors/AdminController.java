package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.CrudController;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.models.request.ClientRequest;
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

    // adminId, clientRequestId
    @PostMapping("approveRequest")
    public Transaction approveRequest(@RequestBody final Map<String, String> data) {
        return service.approveRequest(data);
    }

    // clientRequestId
    @PostMapping("declineRequest/{clientRequestId}")
    public String declineRequest(@PathVariable final Long clientRequestId) {
        return service.declineRequest(clientRequestId);
    }

    @GetMapping("rates")
    public List<Rate> getRates() {
        return service.getRates();
    }

    @GetMapping("bank")
    public List<BankRecord> getBankMoney() {
        return service.getBankMoney();
    }
}
