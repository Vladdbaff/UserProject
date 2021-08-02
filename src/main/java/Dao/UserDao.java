package Dao;
import entity.User;
import java.sql.*;
import java.util.*;

public interface UserDao {
    void deleteAll();

    void createTable() ;

    void deleteTable();

    void add(User user);

    List<User> getAllUsers();

    User getUser(long id);

    void remove(long id);
}
