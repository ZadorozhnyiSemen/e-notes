package com.epam.spring.service;

import java.util.List;

public interface BasePaperEntityService<T> extends BaseEntityService<T> {

    List<T> getAll();

    T getById(Long id);

    T update(T entity);

    T create(T entity);

    void deleteById(Long id);

    List<T> findByName(String name);
}
