package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.actors.Person;
import com.kspt.exchangetrading.services.SystemService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("system")
public class SystemController {

    private final SystemService systemService;

    public SystemController(@NotNull final SystemService systemService){
        this.systemService = systemService;
    }

    // contract: login, password, personType, name, surname
    @PostMapping("signUp")
    public Person signUp(@RequestBody final Map<String, Object> data) {
        return systemService.signUp(data);
    }

    // contract: personType, credentials(login, password)
    @PostMapping("signIn")
    public boolean signIn(@RequestBody final Map<String, Object> data) {
        return systemService.signIn(data);
    }

    // contract: id, personType
    @PostMapping("signOut")
    public boolean signOut(@RequestBody final Map<String, Object> data) {
        return systemService.signOut(data);
    }

}
