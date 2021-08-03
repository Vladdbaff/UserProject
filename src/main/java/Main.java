
import model.*;
import service.UserService;
import service.UserServiceImpl;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        UserService us = new UserServiceImpl();
        us.createUsersTable();
        /*User user1 = new User(1, "Саша", "Пупкин", (byte)31);
        User user2 = new User(2, "Аня", "Ефимук", (byte) 20);
        User user3 = new User(3, "Егор", "Бандура", (byte)18);
        User user4 = new User(4, "Влад", "Жданов", (byte)20);*/
        us.saveUser("Саша", "Пупкин", (byte)31);
        us.saveUser("Аня", "Ефимук", (byte) 20);
        us.saveUser("Егор", "Бандура", (byte)18);
        us.saveUser("Влад", "Жданов", (byte)20);

        List<User> users = us.getAllUsers();
        System.out.println(users);
        us.cleanUsersTable();

        us.dropUsersTable();
        us.getUserDao().closeConnection();

    }
}
