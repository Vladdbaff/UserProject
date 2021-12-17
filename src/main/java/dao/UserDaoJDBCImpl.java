package dao;

import model.*;
import java.util.*;
import util.Util;

import java.sql.*;

public class UserDaoJDBCImpl implements UserDao {
    private final Connection connection = Util.getConnection();


    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM users";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Таблица успешно отчищена!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void createUserTable()  {
        String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(50) NOT NULL," +
                " lastname VARCHAR(50) NOT NULL, age TINYINT NOT NULL)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS users";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO users (name, lastname, age) VALUES (?, ?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                //ignore
            }
            System.err.println("Неудачное добавление элемента");
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastname"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUser(long id) {

        String sql = "SELECT * FROM users WHERE id = ?";
        User user = new User();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet resultSet = statement.executeQuery();

            user.setId(resultSet.getInt("id"));
            user.setName(resultSet.getString("name"));
            user.setLastName(resultSet.getString("lastname"));
            user.setAge(resultSet.getByte("age"));

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void  removeUserById(long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
               connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
