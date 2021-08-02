package service;

import Dao.UserDaoImpl;
import entity.User;

import java.util.List;

public interface UserService {
    void getTable();
    void putUser(User user);
    List<User> getUsers();
    User getUser(long id);
    void deleteAllUsers();
    void dropTable();
}
