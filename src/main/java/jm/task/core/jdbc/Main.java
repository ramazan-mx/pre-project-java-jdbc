package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();

        List<User> userList= new ArrayList<>();
        userList.add(new User("Ramazan", "Rabadanov", (byte) 21));
        userList.add(new User("Ramil", "Smeshnoi", (byte) 10));
        userList.add(new User("Ilon", "Brat", (byte) 20));
        userList.add(new User("Alena", "Dance", (byte) 27));

        service.createUsersTable();
        service.saveUser(userList.get(0).getName(), userList.get(0).getLastName(), userList.get(0).getAge());
        service.saveUser(userList.get(1).getName(), userList.get(1).getLastName(), userList.get(1).getAge());
        service.saveUser(userList.get(2).getName(), userList.get(2).getLastName(), userList.get(2).getAge());
        service.saveUser(userList.get(3).getName(), userList.get(3).getLastName(), userList.get(3).getAge());

        System.out.println(service.getAllUsers());

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
