package com.dev.simper.model;

import java.util.List;

public interface GenericModel<T> {

    T save(T dto);

    List<T> saveAll(List<T> dtos);

    T findById(Long id);

    List<T> findAll();

    void delete(Long id);

    T update(T dto);
}
