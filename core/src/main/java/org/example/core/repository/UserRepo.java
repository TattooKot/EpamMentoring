package org.example.core.repository;

import org.example.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class UserRepo implements CRUDRepo<Integer, User> {

    private final Map<Integer, User> userMap = new ConcurrentHashMap<>();

    public User save(User user) {
        int newId = userMap.size() + 1;
        userMap.put(newId, user);
        return user;
    }

    @Override
    public User getById(Integer id) {
        return userMap.get(id);
    }

    @Override
    public User update(User user) {
        Integer id =
                userMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(user))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        userMap.put(id, user);

        return user;
    }

    @Override
    public void deleteById(Integer id) {
        userMap.remove(id);
    }

}
