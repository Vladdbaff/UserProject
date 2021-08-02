package service;

import model.User;

import java.util.List;

public interface UserService {
    void createUsersTable();

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

    void cleanUsersTable();

    void dropUsersTable();

    void removeUserById(long id);
}
