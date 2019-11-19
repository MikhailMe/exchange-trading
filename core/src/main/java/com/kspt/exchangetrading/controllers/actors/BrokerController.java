package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.AbstractController;
import com.kspt.exchangetrading.models.actors.Broker;
import com.kspt.exchangetrading.models.request.ClientRequest;
import com.kspt.exchangetrading.services.actors.BrokerService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constants.Actor.BROKER)
public class BrokerController extends AbstractController<Broker, BrokerService> {

    public BrokerController(@NotNull BrokerService service) {
        super(service);
    }

    @GetMapping("{brokerId}/checkRequests")
    public List<ClientRequest> checkRequests(@PathVariable final Long brokerId) {
        return service.checkRequests(brokerId);
    }

    // contract: brokerId, clientRequestId
    @PostMapping("validateClientRequest")
    public boolean validateClientRequest(@RequestBody final Map<String, Long> data) {
        return service.validateClientRequest(data);
    }

    // contract: clientRequestId, brokerId
    @PostMapping("approveClientRequest")
    public String approveClientRequest(@RequestBody final Map<String, Long> data) {
        return service.approveOrDeclineClientRequest(data, true);
    }

    // contract: clientRequestId, brokerId
    @PostMapping("declineClientRequest")
    public String declineClientRequest(@RequestBody final Map<String, Long> data) {
        return service.approveOrDeclineClientRequest(data, false);
    }

}
