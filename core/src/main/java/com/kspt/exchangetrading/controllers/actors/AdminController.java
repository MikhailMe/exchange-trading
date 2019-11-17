package com.kspt.exchangetrading.controllers.actors;

import com.kspt.exchangetrading.configuration.Constants;
import com.kspt.exchangetrading.controllers.AbstractController;
import com.kspt.exchangetrading.models.actors.Admin;
import com.kspt.exchangetrading.services.actors.AdminService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.Actor.ADMIN)
public class AdminController extends AbstractController<Admin, AdminService> {

    public AdminController(@NotNull AdminService service) {
        super(service);
    }
}
