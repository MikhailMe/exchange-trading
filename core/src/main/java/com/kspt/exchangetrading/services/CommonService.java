package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.AbstractEntity;

import java.util.List;
import java.util.Optional;

public interface CommonService<T extends AbstractEntity> {

    long count();

    T create(final T entity);

    void deleteAll();

    void deleteById(final Long id);

    boolean existById(final Long id);

    List<T> getAll();

    Optional<T> getById(final Long id);

    T update(final Long id, final T entity);
}
