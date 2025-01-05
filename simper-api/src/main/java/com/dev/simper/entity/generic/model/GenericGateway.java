package com.dev.simper.entity.generic.model;

import java.util.List;
import java.util.Optional;

public interface GenericGateway<T> {
    T save(T model);
    Optional<T> findById(Long id);
    List<T> findAll();
    void delete(Long id);
    T update(T model);
}
