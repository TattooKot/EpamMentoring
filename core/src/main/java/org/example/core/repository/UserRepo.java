package org.example.core.repository;

import org.example.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo implements CRUDRepo<Integer, User> {

    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public Map.Entry<Integer, User> save(User user) {
        int newId = userMap.size() + 1;
        userMap.put(newId, user);
        return Map.entry(newId, user);
    }

    @Override
    public Map.Entry<Integer, User> getById(Integer id) {
        return Map.entry(id, userMap.get(id));
    }

    @Override
    public Map<Integer, User> getAll() {
        return Collections.unmodifiableMap(userMap);
    }

    @Override
    public Map.Entry<Integer, User> update(User user) {
        Integer id =
                userMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(user))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        userMap.put(id, user);

        return Map.entry(id, user);
    }

    @Override
    public void deleteById(Integer id) {
        userMap.remove(id);
    }

}
