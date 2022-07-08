package org.example.core.repository;

import org.example.core.DB;
import org.example.core.model.Event;
import org.example.core.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepo implements CRUDRepo<Long, User> {

    private final Map<Long, User> userMap = DB.userMap;

    public Optional<User> save(User user) {
        if (isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        long newId = userMap.size() + 1;
        user.setId(newId);
        userMap.put(newId, user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        if (userMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(userMap.get(id));
    }

    @Override
    public List<User> getAll() {
        return userMap.values().stream().toList();
    }

    @Override
    public Optional<User> update(User user) {
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

        return Optional.of(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return userMap.remove(id) != null;
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
