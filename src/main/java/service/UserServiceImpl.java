package service;

import Dao.UserDaoImpl;
import entity.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDaoImpl userDao = new UserDaoImpl();
    @Override
    public void getTable() {
        userDao.createTable();
    }

    @Override
    public void putUser(User user) {
        userDao.add(user);
    }

    @Override
    public List<User> getUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUser(long id) {
        return userDao.getUser(id);
    }

    @Override
    public void deleteAllUsers() {
        userDao.deleteAll();
    }

    @Override
    public void dropTable() {
        userDao.deleteTable();
    }
}
