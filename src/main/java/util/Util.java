package util;

import model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


import java.sql.*;
import java.util.*;

public class Util  {
    private final String URL = "jdbc:mysql://localhost:3306/mydbtest";
    private final String LOGIN = "root";
    private final String PASSWORD = "root";
    private Connection connection;
    private static SessionFactory sessionFactory;




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
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                //Properties settings = new Properties();
                configuration.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                             .setProperty(Environment.URL, "jdbc:mysql://localhost:3306/mydbtest")
                             .setProperty(Environment.USER, "root")
                             .setProperty(Environment.PASS, "root")
                             .setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect")
                             .setProperty(Environment.SHOW_SQL, "true")
                             .setProperty(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread")
                             .setProperty(Environment.HBM2DDL_AUTO, "none");

                configuration.addAnnotatedClass(User.class);

                /*ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();*/

                sessionFactory = configuration.buildSessionFactory();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

}


