package com.kspt.exchangetrading.controllers;

import com.kspt.exchangetrading.models.AbstractEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface CommonController<T extends AbstractEntity> {

    @GetMapping("count")
    long count();

    @PostMapping("create")
    T create(@RequestBody T entity);

    @DeleteMapping("deleteAll")
    void deleteAll();

    @DeleteMapping("delete/{id}")
    void deleteById(@PathVariable Long id);

    @GetMapping("exist/{id}")
    boolean existById(@PathVariable Long id);

    @GetMapping("getAll")
    List<T> getAll();

    @GetMapping("get/{id}")
    Optional<T> getById(@PathVariable Long id);

    @PutMapping("update/{id}")
    T update(@PathVariable Long id, @RequestBody T entity);
}
