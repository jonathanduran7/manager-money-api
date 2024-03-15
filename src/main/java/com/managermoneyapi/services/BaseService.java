package com.managermoneyapi.services;

import java.util.List;
import java.util.Optional;

public interface BaseService<T, D> {
    List<T> findAll();
    Optional<T> findById(Long id);
    T save(D d);
    Optional<T> update(Long id, D d);
}
