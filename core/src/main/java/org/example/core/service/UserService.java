package org.example.core.service;

import org.example.core.model.User;
import org.example.core.repository.UserRepo;

import java.util.List;

public class UserService {
    private final UserRepo userRepo = new UserRepo();

    public User save(User user) {
        return userRepo.save(user).orElseGet(User::new);
    }

    public User getById(Long id) {
        return userRepo.getById(id).orElseGet(User::new);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public User update(User user) {
        return userRepo.update(user).orElseGet(User::new);
    }

    public void deleteById(Long id) {
        userRepo.deleteById(id);
    }
}
