package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.AbstractEntity;
import com.kspt.exchangetrading.repositories.CommonRepository;
import org.jetbrains.annotations.NotNull;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public abstract class CrudService<T extends AbstractEntity, R extends CommonRepository<T>>
        implements CommonService<T> {

    @NotNull
    protected final R repository;

    public CrudService(@NotNull final R repository) {
        this.repository = repository;
    }

    public long count() {
        return repository.count();
    }

    public T create(final T entity) {
        return repository.save(entity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    @Transactional
    public void deleteById(final Long id) {
        repository.deleteById(id);
    }

    public boolean existById(final Long id) {
        return repository.existsById(id);
    }

    public Optional<T> getById(final Long id) {
        return repository.findById(id);
    }

    public List<T> getAll() {
        return repository.findAll();
    }

    public T update(final Long id, final T entity) {
        if (!repository.existsById(id)) {
            return null;
        }

        entity.setId(id);
        return repository.save(entity);
    }
}
