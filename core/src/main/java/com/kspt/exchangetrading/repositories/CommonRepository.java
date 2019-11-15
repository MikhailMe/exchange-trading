package com.kspt.exchangetrading.repositories;

import com.kspt.exchangetrading.models.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface CommonRepository<T extends AbstractEntity> extends CrudRepository<T, Long> {

    @Override
    <S extends T> S save (S entity);

    @Override
    List<T> findAll();

    // getById
    // getAll
    // create
    // deleteById
    // deleteAll
    // updateById

}
