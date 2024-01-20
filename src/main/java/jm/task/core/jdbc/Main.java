package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Никита", "Пермяков", (byte) 30);
        System.out.println("User с именем – Никита добавлен в базу данных");

        userService.saveUser("Алена", "Ютаева", (byte) 25);
        System.out.println("User с именем – Алена добавлен в базу данных");

        userService.saveUser("Владислав", "Макаров", (byte) 22);
        System.out.println("User с именем – Владислав добавлен в базу данных");

        userService.saveUser("Петр", "Кунов", (byte) 28);
        System.out.println("User с именем – Петр добавлен в базу данных");

        List<User> users = userService.getAllUsers();
        if (users != null) {
            for (User user : users) {
                System.out.println(user);
            }
        } else {
            System.out.println("Не удалось получить список пользователей.");
        }

        userService.cleanUsersTable();

        userService.dropUsersTable();
    }
}

