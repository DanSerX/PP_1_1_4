package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    private static final UserService us = new UserServiceImpl();

    public static void main(String[] args) {

        us.createUsersTable();

        us.saveUser("Anna", "Brown", (byte) 25);
        us.saveUser("Boris", "Crotov", (byte) 29);
        us.saveUser("Celin", "Dion", (byte) 31);
        us.saveUser("Djoui", "Elington", (byte) 21);

        us.removeUserById(2);
        System.out.println(us.getAllUsers());
        us.cleanUsersTable();
        us.dropUsersTable();
    }
}
