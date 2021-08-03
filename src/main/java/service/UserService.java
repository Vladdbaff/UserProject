package service;

import dao.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public interface UserService {

    //UserDaoJDBCImpl getUserDao();

    void createUsersTable();

    void saveUser(String name, String lastName, byte age);

    List<User> getAllUsers();

    User getUser(long id);

    void cleanUsersTable();

    void dropUsersTable();

    void removeUserById(long id);
}
