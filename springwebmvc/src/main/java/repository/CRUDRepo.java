package repository;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public interface CRUDRepo <T, R>{
    Optional<R> save(R r);
    Optional<R> getById(T id);
    List<R> getAll();
    Optional<R> update(R r);
    boolean deleteById(T id);
}
