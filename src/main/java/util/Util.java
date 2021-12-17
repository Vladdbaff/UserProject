package util;

import java.sql.*;

public abstract class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private static final String LOGIN = "root";
    private static final String PASSWORD = "root";
    private static Connection connection;

    public Util() {
    }

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Соединение не установленно");
        }
        return connection;
    }

    public static void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


