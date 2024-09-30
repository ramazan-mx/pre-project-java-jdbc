package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        String createTable = """
               CREATE TABLE IF NOT EXISTS users (
               id INT AUTO_INCREMENT PRIMARY KEY,
               name VARCHAR(50),
               lastName VARCHAR(50),
               age INT
               )""";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(createTable);
            System.out.println("Таблица успешно создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        String deleteTable = "DROP TABLE IF EXISTS users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(deleteTable);
            System.out.println("Таблица успешно удалена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String addUser = "INSERT INTO users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(addUser)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + "  добавлен в базу данных!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        String deleteUser = "DELETE FROM users WHERE id = ?";

        try (Connection conn = Util.getConnection();
                PreparedStatement statement = conn.prepareStatement(deleteUser)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            System.out.println("User с id " + id + " удалён успешно!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        String selectUser = "SELECT * FROM users";
        List<User> users = new ArrayList<>();

        try (Statement statement = Util.getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(selectUser)) {

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        String deleteUser = "DELETE FROM users";

        try (Statement statement = Util.getConnection().createStatement()) {
            statement.executeUpdate(deleteUser);
            System.out.println("Таблица успешно очищена!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
