package com.epam.spring.service;

import java.util.List;

public interface BasePaperEntityService<T> extends BaseEntityService<T> {
    T getById(Long id);

    void deleteById(Long id);

    List<T> findByName(String name);
}
