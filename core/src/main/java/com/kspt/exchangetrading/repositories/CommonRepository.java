package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.AbstractEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface CommonRepository<T extends AbstractEntity> extends CrudRepository<T, Long> {

    @Override
    long count();

    @Override
    boolean existsById(final Long id);

    @Override
    List<T> findAll();

    @Override
    Optional<T> findById(final Long id);

    @Override
    void deleteAll();

    @Override
    void deleteById(Long id);

    @Override
    <S extends T> S save (@NotNull final S entity);

}
