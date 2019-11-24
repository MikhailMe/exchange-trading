package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Person;
import com.kspt.exchangetrading.models.treasury.BankRecord;
import com.kspt.exchangetrading.models.treasury.Rate;
import com.kspt.exchangetrading.services.SystemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("system")
public final class SystemController {

    private final SystemService service;

    public SystemController(@NotNull final SystemService service) {
        this.service = service;
    }

    // contract: login, password, personType, name, surname
    @PostMapping("signUp")
    public Person signUp(@RequestBody final Map<String, Object> data) {
        return service.signUp(data);
    }

    // contract: personType, credentials(login, password)
    @PostMapping("signIn")
    public boolean signIn(@RequestBody final Map<String, Object> data) {
        return service.signIn(data);
    }

    // contract: id, personType
    @PostMapping("signOut")
    public boolean signOut(@RequestBody final Map<String, Object> data) {
        return service.signOut(data);
    }

    @PostMapping("setRates")
    public List<Rate> setRates() {
        return service.setRates();
    }

    @PostMapping("setBankAssets")
    public List<BankRecord> setBankAssets() {
        return service.setBankAssets();
    }
}
