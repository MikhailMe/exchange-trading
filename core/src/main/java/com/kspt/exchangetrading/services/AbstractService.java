package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;

public abstract class AbstractService<T extends AbstractEntity, R extends CommonRepository<T>>
        implements CommonService<T> {

    @NotNull
    protected final R repository;

    public AbstractService(@NotNull final R repository) {
        this.repository = repository;
    }

    public T save(T entity) {
        return repository.save(entity);
    }
}
