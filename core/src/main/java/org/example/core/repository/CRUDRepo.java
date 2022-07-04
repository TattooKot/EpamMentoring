package org.example.core.repository;

import java.util.Map;

public interface CRUDRepo <T, R>{
    Map.Entry<T, R> save(R r);
    Map.Entry<T, R> getById(T id);
    Map<T, R> getAll();
    Map.Entry<T, R> update(R r);
    void deleteById(T id);
}
