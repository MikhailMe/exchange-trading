package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.CrudController;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.ClientRequest;
import com.kspt.exchangetrading.services.actors.BrokerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.Actor.BROKER)
public final class BrokerController extends CrudController<Broker, BrokerService> {

    public BrokerController(@NotNull BrokerService service) {
        super(service);
    }

    @GetMapping("{brokerId}/checkRequests")
    public List<ClientRequest> checkRequests(@PathVariable final Long brokerId) {
        return service.checkRequests(brokerId);
    }

    // contract: clientRequestId
    @PostMapping("{brokerId}/validateClientRequest")
    public boolean validateClientRequest(@PathVariable final Long brokerId,
                                         @RequestBody final Map<String, Long> data) {
        return service.validateClientRequest(brokerId, data);
    }

    // contract: clientRequestId
    @PostMapping("{brokerId}/approveClientRequest")
    public String approveClientRequest(@PathVariable final Long brokerId,
                                       @RequestBody final Map<String, Long> data) {
        return service.approveOrDeclineClientRequest(brokerId, data, true);
    }

    // contract: clientRequestId
    @PostMapping("{brokerId}/declineClientRequest")
    public String declineClientRequest(@PathVariable final Long brokerId,
                                       @RequestBody final Map<String, Long> data) {
        return service.approveOrDeclineClientRequest(brokerId, data, false);
    }

}
