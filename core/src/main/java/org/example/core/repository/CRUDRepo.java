package org.example.core.repository;

import java.util.Map;
import java.util.Optional;

public interface CRUDRepo <T, R>{
    Optional<Map.Entry<T, R>> save(R r);
    Optional<Map.Entry<T, R>> getById(T id);
    Map<T, R> getAll();
    Optional<Map.Entry<T, R>> update(R r);
    void deleteById(T id);
}
