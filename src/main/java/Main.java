
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import service.UserService;
import service.UserServiceImpl;
import util.Util;

import java.util.List;


public class Main {
    public static void main(String[] args) {

        UserService us = new UserServiceImpl();
        //us.createUsersTable();

        /*us.saveUser("Саша", "Пупкин", (byte)31);
        us.saveUser("Аня", "Ефимук", (byte) 20);
        us.saveUser("Егор", "Бандура", (byte)18);
        us.saveUser("Влад", "Жданов", (byte)20);*/


        List<User> users = us.getAllUsers();
        System.out.println(users);
        //us.cleanUsersTable();

        //us.dropUsersTable();


    }
}
