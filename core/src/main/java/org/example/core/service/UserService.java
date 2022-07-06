package org.example.core.service;

import org.example.core.model.User;
import org.example.core.repository.UserRepo;

import java.util.Map;

public class UserService {
    private final UserRepo userRepo = new UserRepo();

    public Map.Entry<Integer, User> save(User user) {
        return userRepo.save(user).orElseGet(() -> Map.entry(0, new User()));
    }

    public Map.Entry<Integer, User> getById(Integer id) {
        return userRepo.getById(id).orElseGet(() -> Map.entry(0, new User()));
    }

    public Map<Integer, User> getAll() {
        return userRepo.getAll();
    }

    public Map.Entry<Integer, User> update(User user) {
        return userRepo.update(user).orElseGet(() -> Map.entry(0, new User()));
    }

    public void deleteById(Integer id) {
        userRepo.deleteById(id);
    }
}
