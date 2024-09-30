package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static String DB_URL = "jdbc:mysql://localhost:3306/AutoDealerDB";
    private static String USER = "root";
    private static String PASSWORD = "Ramil2015";

    public Util() {

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
