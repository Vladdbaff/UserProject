
import model.*;
import service.UserService;
import service.UserServiceImpl;
import util.Util;

import java.sql.SQLException;
import java.util.*;


public class Main {
    public static void main(String[] args) throws SQLException {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Саша", "Пупкин", (byte)31);
        userService.saveUser("Аня", "Ефимук", (byte) 20);
        userService.saveUser("Егор", "Бандура", (byte)18);
        userService.saveUser("Влад", "Жданов", (byte)20);

        List<User> users = userService.getAllUsers();
        System.out.println(users);
        userService.cleanUsersTable();

        userService.dropUsersTable();
        Util.closeConnection();
    }
}
