package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepo implements CRUDRepo<Long, User> {

    private final Map<Long, User> userMap = DB.userMap;

    public Optional<Map.Entry<Long, User>> save(User user) {
        if (isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        long newId = userMap.size() + 1;
        user.setId(newId);
        userMap.put(newId, user);
        return Optional.of(Map.entry(newId, user));
    }

    @Override
    public Optional<Map.Entry<Long, User>> getById(Long id) {
        if (userMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(Map.entry(id, userMap.get(id)));
    }

    @Override
    public Map<Long, User> getAll() {
        return Collections.unmodifiableMap(userMap);
    }

    @Override
    public Optional<Map.Entry<Long, User>> update(User user) {
        Long id =
                userMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(user))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if (id == 0 || isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        userMap.put(id, user);

        return Optional.of(Map.entry(id, user));
    }

    @Override
    public void deleteById(Long id) {
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
