package com.kspt.exchangetrading.controllers;

import org.springframework.web.bind.annotation.*;

//TODO: don't forget remove me

@RestController
@RequestMapping("test")
public class TestController {

    @GetMapping("test")
    public String foo() {
        return "lol";
    }

}
