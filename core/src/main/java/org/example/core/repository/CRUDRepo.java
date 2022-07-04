package org.example.core.repository;

public interface CRUDRepo <T, R>{
    R save(R r);
    R getById(T id);
    R update(R r);
    void deleteById(T id);
}
