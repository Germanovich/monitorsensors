package com.germanovich.monitorsensors.dao;

import java.util.List;

public interface GenericDao<T, PK> {

    T getById(final PK id);

    void save(final T entity);

    void update(final T entity);

    void delete(final PK id);

    List<T> getAll();
}
