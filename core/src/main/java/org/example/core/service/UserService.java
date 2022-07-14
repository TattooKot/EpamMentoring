package org.example.core.service;

import org.example.core.model.User;
import org.example.core.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public User save(User user) {
        return userRepo.save(user).orElseGet(User::new);
    }

    public User getById(Long id) {
        return userRepo.getById(id).orElseGet(User::new);
    }

    public User getByEmail(String email){
        return userRepo.getByEmail(email).orElseGet(User::new);
    }

    public List<User> getAll() {
        return userRepo.getAll();
    }

    public List<User> getUsersByName(String name, int pageSize, int pageNum) {
        return userRepo.getByName(name, pageSize, pageNum);
    }

    public User update(User user) {
        return userRepo.update(user).orElseGet(User::new);
    }

    public boolean deleteById(Long id) {
        return userRepo.deleteById(id);
    }
}
