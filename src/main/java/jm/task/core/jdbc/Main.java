package jm.task.core.jdbc;

import hibernate_test.entity.Employer;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();

        userService.saveUser("Haba", "Aba", (byte) 15);
        userService.saveUser("Aki", "Shaki", (byte) 36);
        userService.saveUser("Ele", "Eli", (byte) 24);
        userService.saveUser("Umpa", "Vumpa", (byte) 25);

        System.out.println(userService.getAllUsers());

        userService.cleanUsersTable();
        userService.dropUsersTable();

//        UserService service = new UserServiceImpl();
//
//        List<User> userList= new ArrayList<>();
//        userList.add(new User("Ramazan", "Rabadanov", (byte) 21));
//        userList.add(new User("Ramil", "Smeshnoi", (byte) 10));
//        userList.add(new User("Ilon", "Brat", (byte) 20));
//        userList.add(new User("Alena", "Dance", (byte) 27));
//
//        service.createUsersTable();
//        service.saveUser(userList.get(0).getName(), userList.get(0).getLastName(), userList.get(0).getAge());
//        service.saveUser(userList.get(1).getName(), userList.get(1).getLastName(), userList.get(1).getAge());
//        service.saveUser(userList.get(2).getName(), userList.get(2).getLastName(), userList.get(2).getAge());
//        service.saveUser(userList.get(3).getName(), userList.get(3).getLastName(), userList.get(3).getAge());
//
//        System.out.println(service.getAllUsers());
//
//        service.cleanUsersTable();
//        service.dropUsersTable();
    }
}
