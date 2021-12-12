package util;

import java.sql.*;

public class Util {
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";
    private Connection connection;

    public Util() {
    }

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Driver driver = new com.mysql.cj.jdbc.Driver();
            //DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);

        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Соединение не установленно");
        }
        return connection;
    }

    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


