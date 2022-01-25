package dao;
import model.User;

import java.sql.SQLException;
import java.util.*;

public interface UserDao {
    void cleanUsersTable() throws SQLException;

    void createUserTable() throws SQLException;

    void dropUserTable() throws SQLException;

    void saveUser(String name, String lastName, byte age) throws SQLException;

    List<User> getAllUsers();

    User getUser(long id);

    void  removeUserById(long id) throws SQLException;
}
