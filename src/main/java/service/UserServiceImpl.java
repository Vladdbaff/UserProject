package service;

import dao.UserDao;
import dao.UserDaoJDBCImpl;
import model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService{
    private final UserDao userDao = new UserDaoJDBCImpl();

    public UserDao getUserDao() {
        return userDao;
    }

    @Override
    public void createUsersTable() throws SQLException {
        userDao.createUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) throws SQLException {
        userDao.saveUser(name, lastName, age);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void cleanUsersTable() throws SQLException {
        userDao.cleanUsersTable();
    }

    @Override
    public void dropUsersTable() throws SQLException {
        userDao.dropUserTable();
    }

    @Override
    public void removeUserById(long id) throws SQLException {
        userDao.removeUserById(id);
    }


}
