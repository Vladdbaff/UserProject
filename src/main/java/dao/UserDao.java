package dao;
import model.User;

import java.util.*;

public interface UserDao {
    void cleanUsersTable();

    void createUserTable() ;

    void dropUserTable();

    void saveUser(User user);

    List<User> getAllUsers();

    User getUser(long id);

    void  removeUserById(long id);
}
