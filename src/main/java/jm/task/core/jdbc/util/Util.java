package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/AutoDealerDB";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final SessionFactory sessionFactory = buildSessionFactory();

    public Util() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", DB_URL)
                    .setProperty("hibernate.connection.username", USER)
                    .setProperty("hibernate.connection.password", PASSWORD)
                    .setProperty("hibernate.current_session_context_class", "thread")
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed: " + ex);
            throw ex;
        }
    }

    public static Session getSession() {
        return sessionFactory.getCurrentSession();
    }

}
