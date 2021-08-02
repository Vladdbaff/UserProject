package util;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    public Util() {
    }

    public Connection getConnection() {
        try {
            Driver driver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Соединение не установленно");
        }
        return connection;
    }
}
