package Dao;

import entity.*;
import java.util.*;


import java.sql.*;

public class UserDaoImpl extends Util implements UserDao {

    @Override
    public void deleteAll() {
        Connection connection = new Util().getConnection();
        String sql = "DELETE FROM USERS";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно отчищенна!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void createTable()  {
        Connection connection = new Util().getConnection();

        String sql = "CREATE TABLE IF NOT EXISTS USERS (id BIGINT AUTO_INCREMENT PRIMARY KEY, NAME VARCHAR(50) NOT NULL," +
                " LASTNAME VARCHAR(50) NOT NULL, AGE TINYINT NOT NULL)";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица успешно создана!");
        /*} catch (SQLSyntaxErrorException e) {
            System.out.println("Такая таблица уже создана!");*/
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void deleteTable() {
        Connection connection = new Util().getConnection();
        Statement statement = null;
        String sql = "DROP TABLE USERS";

        try {
            statement = connection.createStatement();
            statement.executeUpdate(sql);
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void add(User user)  {
        Connection connection = new Util().getConnection();
        String sql = "INSERT INTO USERS (ID, NAME, LASTNAME, AGE) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(3, user.getLastName());
            statement.setByte(4, user.getAge());
            statement.executeUpdate();
            System.out.println("User с именем – " + user.getName() + " добавлен в базу данных ");
        } catch (SQLException e) {
            System.err.println("Неудачное добавление элемента");
            e.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = new Util().getConnection();
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM USERS";

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

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
        Connection connection = new Util().getConnection();

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
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
        return user;
    }

    @Override
    public void remove(long id) {
        Connection connection = getConnection();

        String sql = "DELETE FROM USERS WERE ID = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    //ignore
                }
            }
        }
    }
}
