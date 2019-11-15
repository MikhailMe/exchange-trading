package com.kspt.exchangetrading.services;

import com.kspt.exchangetrading.models.AbstractEntity;

public interface CommonService<T extends AbstractEntity> {

    T save(T entity);

}
