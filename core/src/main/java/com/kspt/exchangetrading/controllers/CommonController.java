package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.AbstractEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommonController<T extends AbstractEntity> {

    @PostMapping
    T save(@RequestBody T entity);

}
