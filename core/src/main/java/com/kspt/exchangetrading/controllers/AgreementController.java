package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.system.Agreement;
import com.kspt.exchangetrading.services.AgreementService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("agreement")
public class AgreementController extends AbstractController<Agreement, AgreementService> {

    public AgreementController(@NotNull AgreementService service) {
        super(service);
    }
}
