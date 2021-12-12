
import model.*;
import service.UserService;
import service.UserServiceImpl;

import java.util.*;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        /*User user1 = new User(1, "Саша", "Пупкин", (byte)31);
        User user2 = new User(2, "Аня", "Ефимук", (byte) 20);
        User user3 = new User(3, "Егор", "Бандура", (byte)18);
        User user4 = new User(4, "Влад", "Жданов", (byte)20);*/
        userService.saveUser("Саша", "Пупкин", (byte)31);
        userService.saveUser("Аня", "Ефимук", (byte) 20);
        userService.saveUser("Егор", "Бандура", (byte)18);
        userService.saveUser("Влад", "Жданов", (byte)20);

        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();

        userService.dropUsersTable();
        userService.getUserDao().closeConnection();
    }
}
