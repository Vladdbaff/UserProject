package service;

import dao.UserDaoJDBCImpl;
import model.User;

import java.util.List;

public class UserServiceImpl implements UserService{
    private UserDaoJDBCImpl userDao = new UserDaoJDBCImpl();

    public UserDaoJDBCImpl getUserDao() {
        return userDao;
    }

    @Override
    public void createUsersTable() {
        userDao.createUserTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
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
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public void dropUsersTable() {
        userDao.dropUserTable();
    }

    @Override
    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }


}
