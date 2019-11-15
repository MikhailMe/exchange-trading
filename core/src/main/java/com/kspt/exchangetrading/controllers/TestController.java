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


    /*
    *
    * http://localhost:8080/api/client/get/5
    * http://localhost:8080/api/client/getAll
    *
    *
    * http://localhost:8080/api/client/create -> body
    * http://localhost:8080/api/client/update/4 -> body
    *
    *
    * http://localhost:8080/api/client/count
    * http://localhost:8080/api/client/exist/55
    *
    * http://localhost:8080/api/client/delete/1
    * http://localhost:8080/api/client/deleteAll
    *
    * */
}
