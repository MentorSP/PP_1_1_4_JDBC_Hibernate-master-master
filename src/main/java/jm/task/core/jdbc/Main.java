package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Alexander", "Sedykh", (byte) 29);
        userService.saveUser("Mikhail", "Mikhailov", (byte) 35);
        userService.saveUser("Gena", "Bobkov", (byte) 18);
        userService.saveUser("Vladimir", "Putin", (byte) 70);
        for (User user: userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.cleanUsersTable();
        userService.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
