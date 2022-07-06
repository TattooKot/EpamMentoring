package org.example.core.service;

import org.example.core.model.User;
import org.example.core.repository.UserRepo;

import java.util.Map;

public class UserService {
    private final UserRepo userRepo = new UserRepo();

    public Map.Entry<Long, User> save(User user) {
        return userRepo.save(user).orElseGet(() -> Map.entry(0L, new User()));
    }

    public Map.Entry<Long, User> getById(Long id) {
        return userRepo.getById(id).orElseGet(() -> Map.entry(0L, new User()));
    }

    public Map<Long, User> getAll() {
        return userRepo.getAll();
    }

    public Map.Entry<Long, User> update(User user) {
        return userRepo.update(user).orElseGet(() -> Map.entry(0L, new User()));
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
