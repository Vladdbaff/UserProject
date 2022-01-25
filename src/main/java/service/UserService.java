package service;

import dao.UserDao;
import dao.UserDaoJDBCImpl;
import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {

    UserDao getUserDao();

    void createUsersTable() throws SQLException;

    void saveUser(String name, String lastName, byte age) throws SQLException;

    List<User> getAllUsers();

    User getUser(long id);

    void cleanUsersTable() throws SQLException;

    void dropUsersTable() throws SQLException;

    void removeUserById(long id) throws SQLException;
}
