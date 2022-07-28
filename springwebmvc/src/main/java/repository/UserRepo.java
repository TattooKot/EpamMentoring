package repository;

import org.springframework.beans.factory.annotation.Autowired;
import utils.DB;
import model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepo implements CRUDRepo<Long, User> {

    @Autowired
    private DB db;

    public Optional<User> save(User user) {
        if (isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        long newId = db.userMap.size() + 1;
        user.setId(newId);
        db.userMap.put(newId, user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> getById(Long id) {
        if (db.userMap.get(id) == null) {
            return Optional.empty();
        }
        return Optional.of(db.userMap.get(id));
    }

    public Optional<User> getByEmail(String email) {
        return db.userMap.values()
                .stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return db.userMap.values().stream().toList();
    }

    public List<User> getByName(String name, int pageSize, int pageNumber){
        return db.userMap.values()
                .stream()
                .filter(user -> user.getName().contains(name))
                .skip((long) (pageSize - 1) * pageNumber)
                .limit(pageSize)
                .toList();
    }

    @Override
    public Optional<User> update(User user) {
        Long id =
                db.userMap.entrySet()
                        .stream()
                        .filter(entry -> entry.getValue().equals(user))
                        .map(Map.Entry::getKey)
                        .findFirst()
                        .orElse(0L);

        if (id == 0 || isFieldsIncorrect(user)) {
            return Optional.empty();
        }

        db.userMap.put(id, user);

        return Optional.of(user);
    }

    @Override
    public boolean deleteById(Long id) {
        return db.userMap.remove(id) != null;
    }

    private boolean isFieldsIncorrect(User user) {
        User userWithSameEmail = db.userMap.values()
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
