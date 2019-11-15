package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.services.CommonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

public class AbstractController<T extends AbstractEntity, R extends CommonService<T>>
        implements CommonController<T> {

    protected final R service;

    public AbstractController(@NotNull final R service) {
        this.service = service;
    }

    @Override
    public T save(@RequestBody T entity) {
        return service.save(entity);
    }
}
