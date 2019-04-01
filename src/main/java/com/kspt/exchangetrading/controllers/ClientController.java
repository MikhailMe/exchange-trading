package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.configuration.Constants;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.CLIENT)
public class ClientController {

    @RequestMapping("test")
    public String foo() {
        return "lol";
    }

}
