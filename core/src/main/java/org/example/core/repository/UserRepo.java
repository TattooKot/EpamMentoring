package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepo implements CRUDRepo<Integer, User> {

    private final Map<Integer, User> userMap = DB.userMap;

    public Optional<Map.Entry<Integer, User>> save(User user) {
        if (isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        int newId = userMap.size() + 1;
        user.setId(newId);
        userMap.put(newId, user);
        return Optional.of(Map.entry(newId, user));
    }

    @Override
    public Optional<Map.Entry<Integer, User>> getById(Integer id) {
        if (userMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(Map.entry(id, userMap.get(id)));
    }

    @Override
    public Map<Integer, User> getAll() {
        return Collections.unmodifiableMap(userMap);
    }

    @Override
    public Optional<Map.Entry<Integer, User>> update(User user) {
        Integer id =
                userMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(user))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0);

        if (id == 0 || isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        userMap.put(id, user);

        return Optional.of(Map.entry(id, user));
    }

    @Override
    public void deleteById(Integer id) {
        userMap.remove(id);
    }

    private boolean isFieldsIncorrect(User user) {
        User userWithSameEmail = userMap.values()
                .stream()
                .filter(v -> v.getEmail().equals(user.getEmail()))
                .findFirst()
                .orElse(null);

        if(userWithSameEmail != null){
            return true;
        }

        return user.getName() == null || user.getEmail() == null || user.getPhone() == null;
    }
}
