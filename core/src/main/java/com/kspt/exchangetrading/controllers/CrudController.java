package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.services.CommonService;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public class CrudController<T extends AbstractEntity, R extends CommonService<T>>
        implements CommonController<T> {

    protected final R service;

    public CrudController(@NotNull final R service) {
        this.service = service;
    }

    @Override
    public long count() {
        return service.count();
    }

    @Override
    public T create(@RequestBody T entity) {
        return service.create(entity);
    }

    @Override
    public void deleteAll() {
        service.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }

    @Override
    public boolean existById(@PathVariable Long id) {
        return service.existById(id);
    }

    @Override
    public List<T> getAll() {
        return service.getAll();
    }

    @Override
    public Optional<T> getById(final Long id) {
        return service.getById(id);
    }

    @Override
    public T update(final Long id, final T entity) {
        return service.update(id, entity);
    }
}
