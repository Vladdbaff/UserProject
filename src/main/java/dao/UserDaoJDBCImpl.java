package dao;

import model.*;
import java.util.*;
import util.Util;

import java.sql.*;

public class UserDaoJDBCImpl extends Util implements UserDao {
    private Connection connection = getConnection();

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void cleanUsersTable() {
        String sql = "DELETE FROM USERS";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Таблица успешно отчищенна!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUserTable()  {
        String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50) NOT NULL," +
                " LASTNAME VARCHAR(50) NOT NULL, AGE TINYINT NOT NULL)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUserTable() {
        String sql = "DROP TABLE IF EXISTS USERS";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age)  {
        String sql = "INSERT INTO USERS (NAME, LASTNAME, AGE) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных ");
        } catch (SQLException e) {
            System.err.println("Неудачное добавление элемента");
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setLastName(rs.getString("lastname"));
                user.setAge(rs.getByte("age"));

                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUser(long id) {

        String sql = "SELECT * FROM USERS WHERE ID = ?";
        User user = new User();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);

            ResultSet rs = statement.executeQuery();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setLastName(rs.getString("lastname"));
            user.setAge(rs.getByte("age"));

            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
    @Override
    public void  removeUserById(long id) {
        String sql = "DELETE FROM USERS WHERE ID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
