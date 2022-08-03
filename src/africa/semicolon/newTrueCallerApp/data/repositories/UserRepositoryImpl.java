package africa.semicolon.newTrueCallerApp.data.repositories;

import africa.semicolon.newTrueCallerApp.data.models.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UserRepositoryImpl implements UserRepository {
    private int counter;
    private List<User> users = new ArrayList<>();

    @Override
    public User saveUser(User user) {
        var userToUpdate = findById(user.getId());
        if (userToUpdate == null) {
            ++counter;
            user.setId(counter);
            users.add(user);
        } else {
            userToUpdate.setFirstName(user.getFirstName());
            userToUpdate.setLastName(user.getLastName());
        }
        return user;
    }

    @Override
    public void delete(int id) {
        users.removeIf(user -> user.getId() == id);
    }

    @Override
    public void delete(User userToDelete) {
        users.removeIf(user -> user.getId() == userToDelete.getId());
    }

    @Override
    public int count() {
        return users.size();
    }

    @Override
    public User findById(int id) {
        for (var user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User find(String name) {
        for (var user : users) {
            if (Objects.equals(user.getFirstName(), name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> findByLastName(String name) {
        ArrayList<User> usersWithLastNames = new ArrayList<>();
        for (var user : users) {
            if (user.getLastName().equalsIgnoreCase(name)) {
                usersWithLastNames.add(user);
            }
        }
        return usersWithLastNames;
    }

    @Override
    public List<User> findByFirstName(String name) {
        ArrayList<User> usersWithFirstNames = new ArrayList<>();
        for (var user : users) {
            if (user.getFirstName().equalsIgnoreCase(name)) {
                usersWithFirstNames.add(user);
            }
        }
        return usersWithFirstNames;
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findByEmail(String email) {
        for (var user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }
}
